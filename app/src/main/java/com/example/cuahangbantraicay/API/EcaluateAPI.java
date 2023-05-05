package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class EcaluateAPI {
    public static void getAllEcaluateByProductId(Context context,int id, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_USER_URL+ "getAllEcaluateByProductId/"+id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error+"lỗi API này");
            }

        }) {

        };
        requestQueue.add(request);
    }
}
