package com.example.cuahangbantraicay.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.CartActivity;
import com.example.cuahangbantraicay.activity.MainActivity;
import com.example.cuahangbantraicay.adapter.OrderItemAdapter;
import com.example.cuahangbantraicay.model.Order_item;
import com.example.cuahangbantraicay.model.Products;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderItemFragment extends Fragment {
    JSONObject response = new JSONObject();
    JSONArray data= new JSONArray();
    Order_item order_item;
    ArrayList<Order_item> listOrderItem = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    TextView txtTotal,btnBack,btnCart;
    Integer order_id;
    public OrderItemFragment(Integer order_id){
        this.order_id= order_id;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_my_order,container,false);
        recyclerView=view.findViewById(R.id.viewODIT);
        txtTotal=view.findViewById(R.id.txtTotalOrder);
        btnBack=view.findViewById(R.id.btnBackOrder);
        btnCart=view.findViewById(R.id.btnCart);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frameOrder,new OrderFragment());
                ft.commit();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });
        callApi(order_id);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callApi(order_id);
            }
        },100);
        return view;
    }

    public void callApi(Integer order_id){
        VolleyApi volleyApi =new VolleyApi();
        volleyApi.getOrderItem(getContext(), new VolleyApi.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                response = result;
            }
        }, order_id);
        try {
            data=response.getJSONArray("order_item");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson= new Gson();
        Double total = Double.valueOf(0);
        for (int i = 0; i < data.length(); i++) {
            order_item= new Order_item();
            order_item.setOrder_id(order_id);

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
        txtTotal.setText("Total: "+total+"$");
        adapter = new OrderItemAdapter(listOrderItem,getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
