package com.example.cuahangbantraicay.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuahangbantraicay.API.EcaluateAPI;
import com.example.cuahangbantraicay.Modal.Ecaluate;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.ProductAdapter;
import com.example.cuahangbantraicay.adapter.WaitingForReviewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class WaitingForReviewFragment extends Fragment  {

    RecyclerView rcv_waitingForReview;
    WaitingForReviewAdapter waitingForReviewAdapter;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_waiting_for_review, container, false);
        setControl(view);
        createViewWaitingForReview();
        return view;
    }

    private void setControl(View view) {
        rcv_waitingForReview=view.findViewById(R.id.rcv_waitingForReview);
    }
    private void createViewWaitingForReview() {
        waitingForReviewAdapter = new WaitingForReviewAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_waitingForReview.setLayoutManager(linearLayoutManager);
        getListProduct();
        rcv_waitingForReview.setAdapter(waitingForReviewAdapter);
    }

    private void getListProduct() {
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("user_id",-1);
        List<WaitingForReview> listNewReview=new ArrayList<>();

        try {
            EcaluateAPI.getAllNotReviewByUserID(getContext(), user_id, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if ((Boolean) result.get("success")) {
                            JSONArray events = result.getJSONArray("data");
                            JSONObject object= new JSONObject();
                            for (int j = 0; j < events.length(); j++) {
                                object=(JSONObject) events.get(j);
                                WaitingForReview waitingForReview  =new WaitingForReview();
                                waitingForReview.setImg(object.getString("image"));
                                waitingForReview.setName(object.getString("name"));
                                waitingForReview.setQuantity(object.getInt("quantity"));
                                waitingForReview.setPrice((int) object.getDouble("price_in") );
                                waitingForReview.setOrder_id(object.getInt("order_id"));
                                waitingForReview.setProduct_id(object.getInt("product_id"));
                                System.out.println(waitingForReview.getOrder_id());
                                listNewReview.add(waitingForReview);
                            }
                            waitingForReviewAdapter.setData(listNewReview);
                        }
                    } catch (JSONException e) {
                        System.out.println(e + "lỗi nè");
                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}