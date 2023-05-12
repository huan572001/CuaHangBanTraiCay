package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyError;
import com.example.cuahangbantraicay.API.OrderApi;
import com.example.cuahangbantraicay.Modal.Order;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback1;
import com.example.cuahangbantraicay.adapter.CategoryAdapter;
import com.example.cuahangbantraicay.adapter.FirstAdapter;
import com.example.cuahangbantraicay.adapter.SecoundAdapter;
import com.example.cuahangbantraicay.adapter.TabEvaluateAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class SecondFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    RecyclerView recyclerViewOrder2;
    List<Order> orderList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_secound, container, false);
        try {
            CallAPI(view);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return view;
    }

    private void CallAPI(View view) throws JSONException{
        OrderApi.getAllOrderT(getContext(), BASE_URL.BASE_ADMIN_URL + "order-true", new VolleyCallback1() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                orderList = new ArrayList<>();
                JSONArray data = result.getJSONArray("data");
                JSONObject orderObj = new JSONObject();

                for (int i = 0; i < data.length(); i++) {
                    orderObj = (JSONObject) data.get(i);
                    Order tmpC = new Order();
                    tmpC.setId(orderObj.getInt("id"));
                    tmpC.setAddress(orderObj.getString("address"));
                    tmpC.setUser_id(orderObj.getInt("user_id"));
                    orderList.add(tmpC);

                }
//                    System.out.println(categoryList.size());
//                    mapping(view);
                setControl(view);
                setEvent();
            }

            @Override
            public void onError(VolleyError errorMessage) {

            }


        });
    }

    private void setEvent() {
        SecoundAdapter secoundAdapter = new SecoundAdapter(getContext(), orderList);
        recyclerViewOrder2.setHasFixedSize(true);
        recyclerViewOrder2.setAdapter(secoundAdapter);
        recyclerViewOrder2.setLayoutManager(new LinearLayoutManager(getContext()));


    }


    private void setControl(View view) {
        recyclerViewOrder2 = view.findViewById(R.id.recycviewOrder2);
//        viewPager=view.findViewById(R.id.view_pager);
    }
}
