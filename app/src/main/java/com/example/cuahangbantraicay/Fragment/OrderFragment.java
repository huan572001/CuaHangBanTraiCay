package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.OrderAdapter;
import com.example.cuahangbantraicay.model.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderFragment  extends Fragment {
    JSONObject response = new JSONObject();
    JSONArray data = new JSONArray();
    ArrayList<Order>listOrder=new ArrayList<>();
    TextView txtCountOrder;
    Order order = new Order();


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public OrderFragment(){

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    };
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_order,container,false);
        recyclerView=view.findViewById(R.id.orderView);
        txtCountOrder=view.findViewById(R.id.txtCountOrder);
        callApi();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callApi();
            }
        },100);
        return view;
    }
    private void callApi(){
        VolleyApi volleyApi = new VolleyApi();
        volleyApi.getListOrderByUser(getContext(), new VolleyApi.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                response=result;
            }
        });
        try {
            data=response.getJSONArray("order");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length(); i++) {
            order=new Order();
            try {
                order.setAddress(data.getJSONObject(i).getString("address"));
                order.setCreatedAt(data.getJSONObject(i).getString("createdAt"));
                order.setId(Integer.valueOf(data.getJSONObject(i).getString("id")));
                order.setStatus(Boolean.valueOf(data.getJSONObject(i).getString("status")));
                order.setUser_id(Integer.valueOf(data.getJSONObject(i).getString("user_id")));
                order.setUpdatedAt(data.getJSONObject(i).getString("updatedAt"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listOrder.add(order);
        }
        adapter= new OrderAdapter(listOrder,getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}
