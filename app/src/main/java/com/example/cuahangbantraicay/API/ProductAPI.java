package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.Utils.authAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ProductAPI {
    public static void getProductByIDCategory(Context context, VolleyCallback callBack, int id) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_USER_URL+ "getProductByIdCategory/"+String.valueOf(id) ;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        requestQueue.add(request);
    }
    public static void getProductByID(Context context, VolleyCallback callBack, int id) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_USER_URL+ "getProductById/"+id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        requestQueue.add(request);
    }
    public static void getNewProduct(Context context, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_USER_URL+ "getNewProduct";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        requestQueue.add(request);
    }
    public static void getPopularProduct(Context context, VolleyCallback callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_USER_URL+ "getPopularProduct";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callBack.onSuccess(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        requestQueue.add(request);
    }
}
