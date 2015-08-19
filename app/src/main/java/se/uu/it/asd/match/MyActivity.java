package se.uu.it.asd.match;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import se.uu.it.asd.match.adapters.CustomListViewAdapter;
import se.uu.it.asd.match.beans.RowItemService;
import se.uu.it.asd.match.utils.MatchAPI;


public class MyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public final static String REQUEST_INFO = "se.uu.it.asd.match.REQUEST_INFO";
    public final static String REQUEST_SERVICE_LIST = "se.uu.it.asd.match.REQUEST_SERVICE_LIST";
    private static MatchAPI mAPI;
    private CustomListViewAdapter adapter;
    ListView listView;
    List<RowItemService> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        if(savedInstanceState == null){
            mAPI = MatchAPI.getInstance(this);
            rowItems = new ArrayList<RowItemService>();
            listView = (ListView) findViewById(R.id.main_list_view);
            adapter = new CustomListViewAdapter(this, R.layout.list_item, rowItems);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
            fetchList();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(REQUEST_SERVICE_LIST, (ArrayList<? extends Parcelable>) rowItems);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_resource, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        rowItems = savedInstanceState.getParcelableArrayList(REQUEST_SERVICE_LIST);
        adapter.addAll(rowItems);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_update_list:
                fetchList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void fetchList() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo().isConnected()) {
            Log.d("NETWORK", "Fetch task");
            MatchAPI.fetchListTasks(new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject json;
                    JSONArray arr;
                    rowItems = new ArrayList<RowItemService>();
                    for(int i=0; i<response.length(); i++){
                        try {
                            json = response.getJSONObject(i);
                            arr = json.getJSONArray("skills");

                            String[] skills = new String[arr.length()];
                            for(int skill_no=0; skill_no<arr.length(); skill_no++){
                                skills[skill_no] = (String) arr.get(skill_no);
                            }

                            rowItems.add(new RowItemService((int)json.get("id"), (int)json.get("assigned"), skills,
                                    (String) json.get("request"), (String)json.get("user_request"), R.drawable.ic_contact_picture));
                        } catch(JSONException e){
                            Log.d("ERROR", "JSON object cannot be added");
                        }
                    }
                    adapter.clear();
                    adapter.addAll(rowItems);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ServiceItem.class);
        intent.putExtra(REQUEST_INFO, rowItems.get(position));
        startActivity(intent);
    }
}
