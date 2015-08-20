package se.uu.it.asd.match;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import se.uu.it.asd.match.beans.RowItemService;

/**
 * Created by kikofernandezreyes on 19/08/15.
 */
public class ServiceItem extends AppCompatActivity {
    private ImageView mImage;
    private Button mButton;
    private TextView mRequestInfoUser, mRequestInfoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_info);

        // Bind views
        mImage = (ImageView) findViewById(R.id.request_info_icon);
        mRequestInfoUser = (TextView) findViewById(R.id.request_info_user);
        mRequestInfoDescription = (TextView) findViewById(R.id.request_info_description);
        mButton= (Button) findViewById(R.id.confirm_service);

        // Restore info passed from the parent activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        RowItemService row = bundle.getParcelable(MyActivity.REQUEST_INFO);

        // Set the information
        mImage.setImageResource(R.drawable.ic_contact_picture);
        mRequestInfoUser.setText(row.getUser_request());
        mRequestInfoDescription.setText(row.getRequest());
    }



    // Override this method if you want to go to the parent activity without
    // killing all the activities and restarting the app
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void confirmService(View view) {
        Log.d("Confirm", "ACCEPT");
        mButton.setBackgroundColor(Color.GREEN);
        mButton.setClickable(false);
    }
}
