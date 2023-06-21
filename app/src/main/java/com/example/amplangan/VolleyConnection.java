<<<<<<< Updated upstream
ackage com.example.amplangan;
=======
package com.example.amplangan;
>>>>>>> Stashed changes

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

<<<<<<< Updated upstream
public class VollpeyConnection {
=======
public class VolleyConnection {
>>>>>>> Stashed changes

    private static VolleyConnection vInstance;
    private RequestQueue requestQueue;
    private static Context vCtx;

    private VolleyConnection(Context context){
        vCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(vCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleyConnection getInstance(Context context){
        if (vInstance == null){
            vInstance = new VolleyConnection(context);
        }
        return vInstance;
    }

    public<T> void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
