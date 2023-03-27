package com.example.otomarket.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.Map;

public class VolleyRequest {

    private RequestQueue requestQueue;
    int requestMethod;
    private Context context;

    public VolleyRequest(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface VolleyResponseListener {
        void onResponse(JSONArray response);

        void onError(String message);
    }

    public void makeGetRequest(String url, int requestMethod, final Map<String, String> params, final VolleyResponseListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(requestMethod, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}


