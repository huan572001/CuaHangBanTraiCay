package com.example.cuahangbantraicay.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.model.Order_item;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order_item>listOrderItem;


    public OrderItemAdapter(ArrayList<Order_item> listOrderItem, Context context){
        this.listOrderItem=listOrderItem;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_orderitem,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtQty.setText(String.valueOf(listOrderItem.get(position).getQuantity()));
            holder.txtName.setText(listOrderItem.get(position).getProducts().getName());
            holder.txtPrice.setText(String.valueOf(listOrderItem.get(position).getPrice())+"$");
            holder.txtTotal.setText(String.valueOf(listOrderItem.get(position).getPrice()*listOrderItem.get(position).getQuantity())+"$");
            String url=""+listOrderItem.get(position).getProducts().getImage();
            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .into(holder.itemPic);
    }

    @Override
    public int getItemCount() {
        return listOrderItem.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtQty,txtName,txtPrice,txtTotal;
        ImageView itemPic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQty=itemView.findViewById(R.id.txtQty);
            txtName=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtTotal=itemView.findViewById(R.id.txtTotal);
            itemPic=itemView.findViewById(R.id.imgItem);
        }
    }

}
