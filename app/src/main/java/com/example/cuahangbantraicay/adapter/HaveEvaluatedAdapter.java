package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Modal.HaveEvaluated;
import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;

import java.util.ArrayList;
import java.util.List;

public class HaveEvaluatedAdapter extends RecyclerView.Adapter<HaveEvaluatedAdapter.EvaluatedViewHoder>{
    List<String> listHaveEvaluateds;
    Context mContext;
    public HaveEvaluatedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> list) {
        System.out.println(list.size());
        this.listHaveEvaluateds = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public EvaluatedViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_have_evaluated, parent, false);
        return new HaveEvaluatedAdapter.EvaluatedViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluatedViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (listHaveEvaluateds != null) return listHaveEvaluateds.size();
        return 0;
    }
    public class EvaluatedViewHoder extends RecyclerView.ViewHolder {

        public EvaluatedViewHoder(@NonNull View itemView) {
            super(itemView);

        }
    }
}