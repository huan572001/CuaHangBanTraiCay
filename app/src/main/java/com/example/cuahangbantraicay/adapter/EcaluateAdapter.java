package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.Modal.Ecaluate;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;

import java.util.List;

public class EcaluateAdapter extends RecyclerView.Adapter<EcaluateAdapter.EcaluateViewHoder> {
    private List<Ecaluate> List;
    private Context mContext;


    public EcaluateAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Ecaluate> list) {

        this.List = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EcaluateViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_ecaluate, parent, false);
        return new EcaluateViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EcaluateViewHoder holder, int position) {
        Ecaluate ecaluate= List.get(position);
        if(ecaluate.getImage()=="null"){

        }else {
            Glide.with(mContext).load("https://newfreshfoods.com.vn//datafiles/3/2018-02-27/16100958642348_tao-do-my-red-delicious-size-36-44.jpg").into(holder.imgUser);
        }
        holder.tv_name.setText(ecaluate.getName());
        holder.tv_comment.setText(ecaluate.getComment());
        holder.tv_time.setText(ecaluate.getTime());
        holder.rating.setRating(ecaluate.getStars());

    }

    @Override
    public int getItemCount() {
        if (List != null) return List.size();
        return 0;
    }

    public class EcaluateViewHoder extends RecyclerView.ViewHolder {
        RatingBar rating;
        ImageView imgUser;
        TextView tv_name, tv_comment,tv_time;
        public EcaluateViewHoder(@NonNull View itemView) {
            super(itemView);
            rating=itemView.findViewById(R.id.rating);
            imgUser=itemView.findViewById(R.id.img_ecaluate);
            tv_name=itemView.findViewById(R.id.tv_name_user);
            tv_comment=itemView.findViewById(R.id.tv_comment);
            tv_time=itemView.findViewById(R.id.tv_time);

        }
    }

}
