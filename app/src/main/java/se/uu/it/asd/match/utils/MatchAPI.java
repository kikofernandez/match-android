package se.uu.it.asd.match.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import se.uu.it.asd.match.MatchRequestQueue;

/**
 * Created by kikofernandezreyes on 19/08/15.
 */
public final class MatchAPI {
    private final static String URL = "http://10.0.2.2:8000/";
    private static MatchRequestQueue mRequestQueue;
    private static MatchAPI mAPI;
    private Context mContext;

    private MatchAPI(Context context){
        mContext = context;
        mRequestQueue = MatchRequestQueue.getInstance(mContext);
    }

    public static synchronized MatchAPI getInstance(Context context){
        if(mAPI == null) {
            mAPI = new MatchAPI(context);
        }
        return mAPI;
    }

    public static void fetchListTasks(Response.Listener<JSONArray> listener, Response.ErrorListener err){
        mRequestQueue.addToMatchQueue(new JsonArrayRequest(Request.Method.GET, URL+"ask/", null, listener, null));
    }

    public static void fetchListTasks(Response.Listener<JSONArray> listener){
        Response.ErrorListener default_err = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ASYNC", "ERROR");
                VolleyLog.v("NETWORK_ERROR", "Failure when fetchListTask");
            }
        };
        fetchListTasks(listener, default_err);
    }
}
