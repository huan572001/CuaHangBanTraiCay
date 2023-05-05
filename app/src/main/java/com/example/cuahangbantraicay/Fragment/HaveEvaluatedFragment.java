package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuahangbantraicay.Modal.HaveEvaluated;
import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.HaveEvaluatedAdapter;
import com.example.cuahangbantraicay.adapter.WaitingForReviewAdapter;

import java.util.ArrayList;
import java.util.List;


public class HaveEvaluatedFragment extends Fragment {


    RecyclerView rcv_Have_Evaluated;
    HaveEvaluatedAdapter haveEvaluatedAdapter;
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
        getListProduct();
        rcv_Have_Evaluated.setAdapter(haveEvaluatedAdapter);
    }

    private void getListProduct() {
        List<String> listNewProduct=new ArrayList<>();

        listNewProduct.add("huân");
        listNewProduct.add("huân");
        listNewProduct.add("huân");
        listNewProduct.add("huân");
        listNewProduct.add("huân");
        listNewProduct.add("huân");
        haveEvaluatedAdapter.setData(listNewProduct);
    }
}