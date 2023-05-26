package com.example.cuahangbantraicay.API;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangbantraicay.model.Cart_Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyApi {
    public void getJsonObjectA(Context context,  final VolleyCallback callback, Integer u_id ){
        RequestQueue queue = Volley.newRequestQueue(context);
        SharedPreferences sharedPreferences;
        ArrayList<Cart_Item>listCart=new ArrayList<>();


        String url ="http://10.0.2.2:3000/api/cart/getCartItem/"+u_id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(jsonObjectRequest);

    }
    //them vao gio hang
    public static void plusQuantity(Context context,Integer u_id, Integer p_id, Integer qty){
        RequestQueue queue =Volley.newRequestQueue(context);
        String url="http://10.0.2.2:3000/api/cart/plusQty";
        JSONObject body = new JSONObject();
        try {
            body.put("u_id",u_id);
            body.put("p_id",p_id);
            body.put("qty",qty);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, (JSONObject) body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void minusQuantity(Context context,Integer u_id, Integer p_id, Integer qty){
        RequestQueue queue =Volley.newRequestQueue(context);
        String url="http://10.0.2.2:3000/api/cart/minusQty";
        JSONObject body = new JSONObject();
        try {
            body.put("u_id",u_id);
            body.put("p_id",p_id);
            body.put("qty",qty);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, (JSONObject) body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);
    }



    public void deleteCartItem(Context context,Integer u_id, Integer p_id){
        RequestQueue queue =Volley.newRequestQueue(context);
        String url="http://10.0.2.2:3000/api/cart/delete";
        JSONObject body = new JSONObject();
        try {
            body.put("u_id",u_id);
            body.put("p_id",p_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body ,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void placeOrder(Context context,Integer u_id,String address){
        RequestQueue queue =Volley.newRequestQueue(context);
        String url="http://10.0.2.2:3000/api/cart/checkOut";
        JSONObject body=new JSONObject();
        try {
            body.put("user_id",u_id);
            body.put("address",address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void getListOrderByUser(Context context, final VolleyCallback callback, Integer user_id ){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url="http://10.0.2.2:3000/api/order/getListOrder/"+user_id;
        JSONObject body = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(jsonObjectRequest);
    }

    public void getOrderItem(Context context,final VolleyCallback callback,Integer order_id){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://10.0.2.2:3000/api/order/getOrderItem/"+order_id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);
    }
    public void getAllOrder(Context context, final VolleyCallback callback){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url ="http://10.0.2.2:3000/api/statistic/getAllOrder";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public void addToCart(Context context, Integer u_id, Integer p_id){
        RequestQueue queue =Volley.newRequestQueue(context);
        String url="http://10.0.2.2:3000/api/cart/add";
        JSONObject body = new JSONObject();
        try {
            body.put("u_id",u_id);
            body.put("p_id",p_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, (JSONObject) body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsonObjectRequest);

    }


    public interface VolleyCallback {


        void onSuccessResponse(String result);

        void onSuccessResponse(JSONObject result);
    }







}
