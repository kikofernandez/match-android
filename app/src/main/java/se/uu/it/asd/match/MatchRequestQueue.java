package se.uu.it.asd.match;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

/**
 * Created by kikofernandezreyes on 30/07/15.
 */
public class MatchRequestQueue{
    private static Context mContext;
    private static MatchRequestQueue mRequest;
    private static RequestQueue mQueue;

    private MatchRequestQueue(Context context){
        mContext = context;
        mQueue = getRequestQueue();
    }

    private static RequestQueue getRequestQueue(){
        if(mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext);
        }
        return mQueue;
    }


    public static synchronized MatchRequestQueue getInstance(Context context){
        if(mRequest == null){
            mRequest = new MatchRequestQueue(context);
        }
        return mRequest;
    }

    public <T> void addToMatchQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
