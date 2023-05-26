package com.example.cuahangbantraicay.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.CartActivity;
import com.example.cuahangbantraicay.activity.MainActivity;
import com.example.cuahangbantraicay.adapter.ConFirmAdapter;
import com.example.cuahangbantraicay.model.Cart_Item;
import com.example.cuahangbantraicay.model.Products;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConfirmFragment extends Fragment {
    JSONObject response=new JSONObject();
    ArrayList<Cart_Item>listCart=new ArrayList<>();
    JSONArray data = new JSONArray();
    Cart_Item cartItem ;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter adapter;
    ConFirmAdapter conFirmAdapter;

    TextView btnBack,btnPlaceOrder,txtTotalItem,txtTotalAmount,txtShip;
    String address;

    public ConfirmFragment(String address){
        this.address=address;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        txtTotalAmount=view.findViewById(R.id.txtTotalAmount);
        txtTotalItem=view.findViewById(R.id.txtTotalItem);
        txtShip=view.findViewById(R.id.txtShipping);
        recyclerView=view.findViewById(R.id.viewConfirm);
        recyclerViewlayoutManager = new LinearLayoutManager(getContext());
        callApi();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                callApi();
            }
        }, 100);
        btnBack=view.findViewById(R.id.btnBack);
        btnPlaceOrder=view.findViewById(R.id.btnPlaceOrder);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CartActivity.class));
            }
        });
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences;
                sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                int user_id = sharedPreferences.getInt("user_id",-1);
                VolleyApi volleyApi = new VolleyApi();
                volleyApi.placeOrder(getContext(),user_id,address);
                Toast toast=Toast.makeText(getContext(), "Place Order Success", Toast.LENGTH_LONG);
                toast.show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                }, 3000);
            }
        });

        return view;
    }

    private void callApi() {
        VolleyApi volleyApi = new VolleyApi();
        Double total = Double.valueOf(0);
        SharedPreferences sharedPreferences;
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("user_id",-1);



        volleyApi.getJsonObjectA(getContext(), new VolleyApi.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                response=result;
            }
        },user_id);
        try {
            data=response.getJSONArray("data");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        Gson gson = new Gson();


        for (int i = 0; i <data.length() ; i++) {
            cartItem = new Cart_Item();
            try {
                cartItem.setQuantity(data.getJSONObject(i).getInt("quantity"));
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

        if(listCart.size()>0){
            for (int i = 0; i <listCart.size() ; i++) {
                total+=listCart.get(i).getQuantity()*listCart.get(i).getProducts().getPrice_sell();
            }
        }
        txtTotalAmount.setText(""+(total+5));
        txtTotalItem.setText(""+total);
        txtShip.setText(""+5);

        adapter= new ConFirmAdapter(listCart,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
