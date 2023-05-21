package com.example.cuahangbantraicay.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.Modal.User;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthAPI {
    public static void Rigister(Context context, VolleyCallback callBack, User user) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_URL+ "register";
        JSONObject body = new JSONObject();
        body.put("username",user.getUsername());
        body.put("password",user.getPassword());
        body.put("name",user.getName());
        body.put("email",user.getEmail());
        body.put("phone",user.getPhone());
        body.put("role",user.getRole());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body,
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

        };
        requestQueue.add(request);
    }
    public static void SendMail(Context context, VolleyCallback callBack, String email) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_URL+ "sendMail";
        JSONObject body = new JSONObject();
        body.put("email",email);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body,
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

        };
        requestQueue.add(request);
    }
    public static void VerifiOTP(Context context, VolleyCallback callBack, String otp,String token) throws JSONException {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL.BASE_URL+ "verifiOTP";
        JSONObject body = new JSONObject();
        body.put("otp",otp);
        body.put("token",token);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body,
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

        };
        requestQueue.add(request);
    }
}
