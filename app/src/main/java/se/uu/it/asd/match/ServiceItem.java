package se.uu.it.asd.match;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import se.uu.it.asd.match.beans.RowItemService;

/**
 * Created by kikofernandezreyes on 19/08/15.
 */
public class ServiceItem extends AppCompatActivity {
    private ImageView mImage;
    private TextView mRequestInfoUser, mRequestInfoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_info);

        mImage = (ImageView) findViewById(R.id.request_info_icon);
        mRequestInfoUser = (TextView) findViewById(R.id.request_info_user);
        mRequestInfoDescription = (TextView) findViewById(R.id.request_info_description);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        RowItemService row = (RowItemService) bundle.getParcelable(MyActivity.REQUEST_INFO);

        mImage.setImageResource(R.drawable.ic_contact_picture);
        mRequestInfoUser.setText(row.getUser_request());
        mRequestInfoDescription.setText(row.getRequest());
    }

}
