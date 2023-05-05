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
import com.example.cuahangbantraicay.model.Cart_Item;

import java.util.ArrayList;

public class ConFirmAdapter extends RecyclerView.Adapter<ConFirmAdapter.ViewHolder> {
    ArrayList<Cart_Item> listCart=new ArrayList<Cart_Item>();
    Context context;
    public ConFirmAdapter(ArrayList<Cart_Item>listCart, Context context){
        this.listCart=listCart;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()) .inflate(R.layout.viewholder_check_out, parent, false);


        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(listCart.get(position).getProducts().getName());
        holder.txtQty.setText(""+listCart.get(position).getQuantity());
        holder.txtPrice.setText(""+listCart.get(position).getProducts().getPrice_sell());
        holder.txtTotal.setText(""+(listCart.get(position).getProducts().getPrice_sell()*listCart.get(position).getQuantity()));
        String url="";
         url += listCart.get(position).getProducts().getImage();
        if(url!=""){

            Glide.with(holder.itemView.getContext())
                    .load(url)
                    .into(holder.imageItem);
        }

    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtPrice,txtQty,txtTotal;
        ImageView imageItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotal=itemView.findViewById(R.id.txtTotal);
            txtName=itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtQty=itemView.findViewById(R.id.txtQty);
            imageItem=itemView.findViewById(R.id.imgItem);



        }
    }
}
