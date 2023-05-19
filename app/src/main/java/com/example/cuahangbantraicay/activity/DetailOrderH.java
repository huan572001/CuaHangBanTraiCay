package com.example.cuahangbantraicay.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.OrderItemAdapter;
import com.example.cuahangbantraicay.model.Order_item;
import com.example.cuahangbantraicay.model.Products;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailOrderH extends AppCompatActivity {
    JSONObject response = new JSONObject();
    JSONArray data= new JSONArray();
    Order_item order_item;
    ArrayList<Order_item> listOrderItem = new ArrayList<>();

    public static Boolean isActive = false ;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    TextView  textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detailsh);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView=findViewById(R.id.order_detailsh);
        textView = findViewById(R.id.backOrderItem);
        recyclerView.setLayoutManager(linearLayoutManager);
        callApi();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callApi();
            }
        },100);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isActive = true;
                Intent intent = new Intent(DetailOrderH.this, Admin.class);
                startActivity(intent);
            }
        });

    }
    public void callApi(){
        VolleyApi volleyApi =new VolleyApi();
        volleyApi.getOrderItem(DetailOrderH.this, new VolleyApi.VolleyCallback() {
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

        adapter = new OrderItemAdapter(listOrderItem, DetailOrderH.this);

        recyclerView.setAdapter(adapter);


    }





}