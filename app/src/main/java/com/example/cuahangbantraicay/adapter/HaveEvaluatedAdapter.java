package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.Modal.Ecaluate;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.DetailsProduct;

import java.util.List;

public class HaveEvaluatedAdapter extends RecyclerView.Adapter<HaveEvaluatedAdapter.EvaluatedViewHoder>{
    List<Ecaluate> listHaveEvaluateds;
    Context mContext;
    public HaveEvaluatedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Ecaluate> list) {
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
        holder.ll_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsProduct.class);
                intent.putExtra("idProduct",1);
                mContext.startActivity(intent);
            }
        });
        if(listHaveEvaluateds.get(position).getImage()==null){
            Glide.with(mContext).load("https://newfreshfoods.com.vn//datafiles/3/2018-02-27/16100958642348_tao-do-my-red-delicious-size-36-44.jpg").into(holder.img_user);
        }else {
            Glide.with(mContext).load(listHaveEvaluateds.get(position).getImage()).into(holder.img_user);
        }
        if(listHaveEvaluateds.get(position).getImgProduct()==null){
            Glide.with(mContext).load("https://newfreshfoods.com.vn//datafiles/3/2018-02-27/16100958642348_tao-do-my-red-delicious-size-36-44.jpg").into(holder.tmg_product);
        }else {
            Glide.with(mContext).load(listHaveEvaluateds.get(position).getImgProduct()).into(holder.tmg_product);
        }
        holder.tv_userName.setText(listHaveEvaluateds.get(position).getName());
        holder.rating_evalua.setRating(listHaveEvaluateds.get(position).getStars());
        holder.tv_time.setText(listHaveEvaluateds.get(position).getTime());
        holder.tv_review.setText(listHaveEvaluateds.get(position).getComment());
        holder.tv_nameProduct.setText(listHaveEvaluateds.get(position).getNameProduct());
    }

    @Override
    public int getItemCount() {
        if (listHaveEvaluateds != null) return listHaveEvaluateds.size();
        return 0;
    }
    public class EvaluatedViewHoder extends RecyclerView.ViewHolder {
        LinearLayout ll_product;
        ImageView img_user,tmg_product;
        TextView tv_userName,tv_time,tv_review,tv_nameProduct;
        RatingBar rating_evalua;
        public EvaluatedViewHoder(@NonNull View itemView) {
            super(itemView);
            ll_product=itemView.findViewById(R.id.ll_product);
            img_user=itemView.findViewById(R.id.img_user);
            tv_userName=itemView.findViewById(R.id.tv_userName);
            rating_evalua=itemView.findViewById(R.id.rating_evalua);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_review=itemView.findViewById(R.id.tv_review);
            tmg_product=itemView.findViewById(R.id.tmg_product);
            tv_nameProduct=itemView.findViewById(R.id.tv_nameProduct);
        }
    }
}
