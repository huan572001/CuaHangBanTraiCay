package com.example.cuahangbantraicay.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.CartA;
import com.example.cuahangbantraicay.model.Cart_Item;
import com.example.cuahangbantraicay.model.Products;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private TextView textView7;
    private TextView textView5,checkOutBtn,homeBtn;
    private RecyclerView recyclerViewCartList;
    private TextView itemTotalTxt;
    private JSONObject response=new JSONObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewCartList=findViewById(R.id.viewCart);
        recyclerViewCartList.setLayoutManager(linearLayoutManager);
        itemTotalTxt=findViewById(R.id.itemTotalTxt);
        textView7=findViewById(R.id.textView7);
        textView5=findViewById(R.id.textView5);
        homeBtn=findViewById(R.id.homeBtn);
        checkOutBtn=findViewById(R.id.checkOutBtn);
        clickApi();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                clickApi();
            }
        }, 100);
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,CheckoutActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });
    }
    private void clickApi() {
        VolleyApi volleyApi = new VolleyApi();

        ArrayList<Cart_Item>listCart = new ArrayList<>();

        SharedPreferences sharedPreferences;
        sharedPreferences = CartActivity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("user_id",-1);
        volleyApi.getJsonObjectA(CartActivity.this, new VolleyApi.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                response=result;
                JSONArray data = new JSONArray();
                Cart_Item cartItem;
                try {
                    data=response.getJSONArray("data");
                } catch (JSONException ex) {
                    System.out.println("================loi o day============");
                    ex.printStackTrace();
                }
                Gson gson = new Gson();


                for (int i = 0; i <data.length() ; i++) {
                    cartItem = new Cart_Item();
                    try {
                        cartItem.setQuantity(data.getJSONObject(i).getInt("quantity"));
                        cartItem.setUser_id(data.getJSONObject(i).getInt("user_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        cartItem.setProducts(gson.fromJson(String.valueOf(data.getJSONObject(i).getJSONObject("product")), Products.class));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listCart.add(cartItem);
                }


                adapter= new CartA(listCart,CartActivity.this);
                recyclerViewCartList.setAdapter(adapter);
            }
        },user_id);


    }






}
