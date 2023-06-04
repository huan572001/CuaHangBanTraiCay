package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Utils.VolleyCallback1;
import com.example.cuahangbantraicay.Utils.authAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class OrderApi {

    // lay ra tat ca cac order bang null
    public static void getAllOrder(Context context, String url, VolleyCallback1 callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
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
                        callBack.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    // lay ra tat ca cac order accept
    public static void getAllOrderT(Context context, String url, VolleyCallback1 callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
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
                        callBack.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }


    // lay ra tat ca cac order bi cancle
    public static void getAllOrderF(Context context, String url, VolleyCallback1 callBack) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle response
                        try {
                            callBack.onSuccess(response);
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
                        callBack.onError(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                authAPI authAPI = new authAPI(context);
                return authAPI.getAuthorizationHeaders();
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);

    }

    // accept cho 1 order
    public static void AcceptOrder(Context context, String url, VolleyCallback1 callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
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

    // huy mot order
    public static void CancleOrder(Context context, String url, VolleyCallback1 callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
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

    // detail order
    public static void getById(Context context, String url, VolleyCallback1 callback) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
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
