package se.uu.it.asd.match;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import se.uu.it.asd.match.adapters.CustomListViewAdapter;
import se.uu.it.asd.match.beans.RowItemService;
import se.uu.it.asd.match.utils.MatchAPI;


public class MyActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "se.uu.it.asd.match.MESSAGE";
    private static MatchAPI mAPI;
    ListView listView;
    List<RowItemService> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mAPI = MatchAPI.getInstance(this);
        rowItems = new ArrayList<RowItemService>();
        listView = (ListView) findViewById(R.id.main_list_view);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        try {
            fetchList(null);
        }catch (JSONException e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_resource, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private String getStringOrNull(JSONArray response, int index) {
        if(response.isNull(index)){
            return "null";
        }else{
            try {
                return response.getString(index);
            } catch (JSONException e) {
                e.printStackTrace();
                return "ERROR";
            }
        }
    }

    public void fetchList(View view) throws JSONException {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo().isConnected()) {
            MatchAPI.fetchListTasks(new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    ArrayList<RowItemService> rowItems = new ArrayList<RowItemService>();
                    JSONObject jo;
                    for(int i=0; i<response.length(); i++){
                        try {
                            jo = response.getJSONObject(i);
                            rowItems.add(new RowItemService((int)jo.get("id"), (int)jo.get("assigned"), null,
                                    (String) jo.get("request"), (String)jo.get("user_request"), R.drawable.ic_contact_picture));
                        } catch(JSONException e){

                        }
                    }

                    CustomListViewAdapter adapter = new CustomListViewAdapter(getApplicationContext(), R.layout.list_item, rowItems);
                    listView.setAdapter(adapter);
                }
            });
        } else {
            buildAlertDialog("Error", "No internet connection");
        }
    }

    void buildAlertDialog(String title, String message){
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}
