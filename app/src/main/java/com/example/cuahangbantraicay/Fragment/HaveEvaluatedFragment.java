package com.example.cuahangbantraicay.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuahangbantraicay.API.EcaluateAPI;
import com.example.cuahangbantraicay.Modal.Ecaluate;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.HaveEvaluatedAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HaveEvaluatedFragment extends Fragment {


    RecyclerView rcv_Have_Evaluated;
    HaveEvaluatedAdapter haveEvaluatedAdapter;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_have_evaluated, container, false);
        setControl(view);
        createViewHaveEveluated();
        setEvent();
        return view;
    }

    private void setEvent() {

    }

    private void setControl(View view) {
        rcv_Have_Evaluated=view.findViewById(R.id.rcv_have_evaluated);
    }
    private void createViewHaveEveluated() {
        haveEvaluatedAdapter = new HaveEvaluatedAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_Have_Evaluated.setLayoutManager(linearLayoutManager);
        getListEvaluate();
        rcv_Have_Evaluated.setAdapter(haveEvaluatedAdapter);
    }

    private void getListEvaluate() {
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("user_id",-1);
        String userName= sharedPreferences.getString("userName",null);
        String img= sharedPreferences.getString("img",null);
        List<Ecaluate> listNewEcaluate=new ArrayList<>();
        try {
            EcaluateAPI.getAllEcaluateNotReviewByUserID(getContext(), user_id, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {

                    try {
                        if ((Boolean) result.get("success")) {
                            JSONArray events = result.getJSONArray("data");
                            JSONObject object= new JSONObject();
                            for (int j = 0; j < events.length(); j++) {
                                object=(JSONObject) events.get(j);
                                Ecaluate ecaluate=new Ecaluate();
                                ecaluate.setProductId(object.getInt("product_id"));
                                ecaluate.setStars(object.getInt("stars"));
                                ecaluate.setComment(object.getString("review"));
                                ecaluate.setNameProduct(object.getString("name"));
                                ecaluate.setImage(object.getString("image"));
                                ecaluate.setName(userName);
                                ecaluate.setImage(img);

                                listNewEcaluate.add(ecaluate);
                            }
                            haveEvaluatedAdapter.setData(listNewEcaluate);
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