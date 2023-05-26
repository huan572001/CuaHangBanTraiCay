package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.DangNhap;
import com.example.cuahangbantraicay.activity.EvaluateReview;
import com.example.cuahangbantraicay.activity.MainActivity;

import java.io.Serializable;
import java.util.List;

public class WaitingForReviewAdapter extends RecyclerView.Adapter<WaitingForReviewAdapter.WaitingForReviewViewHoder> {
    Context mContext;
    List<WaitingForReview> List ;
    public WaitingForReviewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<WaitingForReview> list) {
        this.List = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WaitingForReviewViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_waiting_for_review, parent, false);
        return new WaitingForReviewViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitingForReviewViewHoder holder, int position) {
        WaitingForReview
                waitingForReview=List.get(position);
        Glide.with(mContext).load(waitingForReview.getImg()).into(holder.img_WFR);
        holder.tv_name.setText(waitingForReview.getName());
        holder.tv_total.setText(waitingForReview.getQuantity()+" sản phẩm thành tiền: "+(waitingForReview.getPrice()*waitingForReview.getQuantity()));
        holder.tv_quality.setText("x"+waitingForReview.getQuantity());
        holder.tv_price.setText(waitingForReview.getPrice()+"vnd");
        holder.btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EvaluateReview.class);
                intent.putExtra("review",  waitingForReview);
                System.out.println(waitingForReview.getOrder_id()+"================");
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (List != null) return List.size();
        return 0;
    }

    public class WaitingForReviewViewHoder extends RecyclerView.ViewHolder {
        ImageView img_WFR;
        TextView tv_name,tv_price,tv_quality,tv_total;
        Button btn_review;
        public WaitingForReviewViewHoder(@NonNull View itemView) {
            super(itemView);
            img_WFR=itemView.findViewById(R.id.img_WFR);
            tv_name=itemView.findViewById(R.id.tv_WFR_name);
            tv_quality=itemView.findViewById(R.id.tv_WFR_quality);
            tv_price=itemView.findViewById(R.id.tv_WFR_price);
            tv_total=itemView.findViewById(R.id.tv_WFR_total);
            btn_review=itemView.findViewById(R.id.btn_review);
        }
    }
}
