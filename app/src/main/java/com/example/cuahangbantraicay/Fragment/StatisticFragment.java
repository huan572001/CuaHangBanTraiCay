package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.StatisticAdapter;
import com.example.cuahangbantraicay.model.Order;
import com.example.cuahangbantraicay.model.Statistic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StatisticFragment extends Fragment {
    JSONObject response= new JSONObject();
    ArrayList<Order> listOrder = new ArrayList<>();
    ArrayList<Statistic> listStatistic =new ArrayList<>();
    Order order;
    Statistic statistic;
    JSONArray dataStatistic = new JSONArray();
    JSONArray dataOrder=new JSONArray();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public StatisticFragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_statistic,container,false);
        recyclerView= view.findViewById(R.id.statisticView);
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
        volleyApi.getAllOrder(getContext(), new VolleyApi.VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {

            }

            @Override
            public void onSuccessResponse(JSONObject result) {
                response=result;
            }
        });
        try {
            dataOrder=response.getJSONArray("order");
            dataStatistic= response.getJSONArray("statistic");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <dataOrder.length() ; i++) {
            order = new Order();
            statistic = new Statistic();
            try {
                statistic.setPriceIn(Double.valueOf(dataStatistic.getJSONObject(i).getString("price_in")));
                statistic.setPriceSell(Double.valueOf(dataStatistic.getJSONObject(i).getString("price_out")));
                order.setCreatedAt(dataOrder.getJSONObject(i).getString("createdAt"));
                order.setId(Integer.valueOf(dataOrder.getJSONObject(i).getString("id")));
                order.setStatus(Boolean.valueOf(dataOrder.getJSONObject(i).getString("status")));
                order.setUser_id(Integer.valueOf(dataOrder.getJSONObject(i).getString("user_id")));
                order.setUpdatedAt(dataOrder.getJSONObject(i).getString("updatedAt"));
                order.setAddress(dataOrder.getJSONObject(i).getString("address"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listOrder.add(order);
            listStatistic.add(statistic);
        }
        System.out.println(listOrder);
        adapter = new StatisticAdapter(listOrder,listStatistic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
