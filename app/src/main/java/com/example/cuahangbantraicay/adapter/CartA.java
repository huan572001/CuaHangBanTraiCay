package com.example.cuahangbantraicay.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.VolleyApi;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.CartActivity;
import com.example.cuahangbantraicay.model.Cart_Item;

import java.util.ArrayList;

public class CartA extends RecyclerView.Adapter<CartA.ViewHolder> {
    private  Context context;
    ArrayList<Cart_Item> cartList;

    public CartA(ArrayList<Cart_Item> cartList, Context context) {

        this.cartList = cartList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart,parent,false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtQty.setText(String.valueOf(cartList.get(position).getQuantity()));
        holder.itemName.setText(cartList.get(position).getProducts().getName());
        holder.txtPrice.setText(String.valueOf(cartList.get(position).getProducts().getPrice_sell()));
        holder.sub_total.setText(String.valueOf(cartList.get(position).getQuantity()*cartList.get(position).getProducts().getPrice_sell()));


        String url= cartList.get(position).getProducts().getImage();
        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.itemPic);
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer qty ;
                qty=Integer.parseInt(holder.txtQty.getText().toString());
                if(cartList.get(position).getProducts().getQuantity()>qty){
                    VolleyApi volleyApi = new VolleyApi();
                    volleyApi.plusQuantity(view.getContext(), cartList.get(position).getUser_id(),cartList.get(position).getProducts().getId(),qty);
                    holder.txtQty.setText(String.valueOf(qty+1));
                }else{
                    Toast toast=Toast.makeText(view.getContext(), "cannot exceed the quantity in stock ", Toast.LENGTH_LONG);
                    toast.show();

                }

            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer qty;
                qty=Integer.parseInt(holder.txtQty.getText().toString());
                if(qty>=2){
                    VolleyApi volleyApi = new VolleyApi();
                    volleyApi.minusQuantity(view.getContext(),cartList.get(position).getUser_id(),cartList.get(position).getProducts().getId(),qty);
                    holder.txtQty.setText(String.valueOf(qty-1));
                }



            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyApi volleyApi = new VolleyApi();
                volleyApi.deleteCartItem(view.getContext(), cartList.get(position).getUser_id(),cartList.get(position).getProducts().getId());

                context.startActivity(new Intent(context,CartActivity.class));


            }
        });holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer qty ;
                qty=Integer.parseInt(holder.txtQty.getText().toString());
                if(cartList.get(position).getProducts().getQuantity()>qty){
                    VolleyApi volleyApi = new VolleyApi();
                    volleyApi.plusQuantity(view.getContext(), cartList.get(position).getUser_id(),cartList.get(position).getProducts().getId(),qty);
                    holder.txtQty.setText(String.valueOf(qty+1));
                }else{
                    Toast toast=Toast.makeText(view.getContext(), "cannot exceed the quantity in stock ", Toast.LENGTH_LONG);
                    toast.show();

                }

            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer qty;
                qty=Integer.parseInt(holder.txtQty.getText().toString());
                if(qty>=2){
                    VolleyApi volleyApi = new VolleyApi();
                    volleyApi.minusQuantity(view.getContext(),cartList.get(position).getUser_id(),cartList.get(position).getProducts().getId(),qty);
                    holder.txtQty.setText(String.valueOf(qty-1));
                }



            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolleyApi volleyApi = new VolleyApi();
                volleyApi.deleteCartItem(view.getContext(), cartList.get(position).getUser_id(),cartList.get(position).getProducts().getId());
                context.startActivity(new Intent(context,CartActivity.class));


            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemPic;
        TextView itemName,txtQty,btnPlus,btnMinus,btnDelete,txtPrice,sub_total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPic = itemView.findViewById(R.id.product_image);
            itemName = itemView.findViewById(R.id.product_title);
            txtQty=itemView.findViewById(R.id.quantity);
            btnPlus=itemView.findViewById(R.id.quantity_plus);
            btnMinus=itemView.findViewById(R.id.quantity_minus);
            btnDelete=itemView.findViewById(R.id.cart_delete);
            txtPrice=itemView.findViewById(R.id.product_price);
            sub_total=itemView.findViewById(R.id.sub_total);
        }
    }


}
