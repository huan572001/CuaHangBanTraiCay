package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.Utils.VolleyCallback1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

public class ThongKeApi {
    public static void getThongKe(Context context, String url, VolleyCallback1 callback) throws JarException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
//                        System.out.println("Nam : " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        callback.onError(error);
                    }
                });
        requestQueue.add(request);
    }
}