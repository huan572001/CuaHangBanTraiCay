package com.example.cuahangbantraicay.activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.CartA;
import com.example.cuahangbantraicay.adapter.OrderAdapter;
import com.example.cuahangbantraicay.adapter.OrderItemAdapter;
import com.example.cuahangbantraicay.model.Order;
import com.example.cuahangbantraicay.model.Order_item;
import com.example.cuahangbantraicay.model.Products;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailOrder extends AppCompatActivity {
    JSONObject response = new JSONObject();
    JSONArray data= new JSONArray();
    Order_item order_item;
    ArrayList<Order_item> listOrderItem = new ArrayList<>();


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView=findViewById(R.id.order_details);
        recyclerView.setLayoutManager(linearLayoutManager);
        callApi();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callApi();
            }
        },100);


    }
    public void callApi(){
        VolleyApi volleyApi =new VolleyApi();
        volleyApi.getOrderItem(DetailOrder.this, new VolleyApi.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                response = result;
            }
        }, 1);
        try {
            data=response.getJSONArray("order_item");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson= new Gson();
        Double total = Double.valueOf(0);
        for (int i = 0; i < data.length(); i++) {
            order_item= new Order_item();
            order_item.setOrder_id(1);

            try {
                order_item.setPrice(Double.valueOf(data.getJSONObject(i).getString("price")));
                order_item.setQuantity(Integer.valueOf(data.getJSONObject(i).getString("quantity")));
                order_item.setProducts(gson.fromJson(String.valueOf(data.getJSONObject(i).getJSONObject("product")), Products.class));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            total+=order_item.getPrice()*order_item.getQuantity();
            listOrderItem.add(order_item);
        }

        adapter = new OrderItemAdapter(listOrderItem,DetailOrder.this);

        recyclerView.setAdapter(adapter);


    }





}