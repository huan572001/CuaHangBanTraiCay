package com.example.cuahangbantraicay.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.API.OrderApi;
import com.example.cuahangbantraicay.Modal.Order;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback1;
import com.example.cuahangbantraicay.activity.Admin;
import com.example.cuahangbantraicay.activity.DetailOrder;
import com.example.cuahangbantraicay.activity.DetailOrderH;
import com.example.cuahangbantraicay.activity.DetailsProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolderAdmin>{
    Context mContext;

    public static Boolean isActive = false ;
    List<Order> orderList;

    public FirstAdapter(Context mContext,   List<Order> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public FirstAdapter.ViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_first, parent, false);
        FirstAdapter.ViewHolderAdmin viewHolderAdmin = new FirstAdapter.ViewHolderAdmin(view);
        return viewHolderAdmin;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdmin holder, int position) {
        Order order = orderList.get(position);
        holder.diachi.setText(order.getAddress());
        holder.idorder.setText(String.valueOf(order.getId()));
        holder.idkhach.setText(String.valueOf(order.getUser_id()));

        holder.ivAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Accept", Toast.LENGTH_SHORT).show();
                clickOrderAccept(order.getId());
//                new FirstAdapter(mContext,orderList);


            }
        });
        holder.ivCancle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.alert_cancle);
                Button btnYes = dialog.findViewById(R.id.YesCancle);
                Button btnNo = dialog.findViewById(R.id.NoCancle);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(mContext, "Cancle", Toast.LENGTH_SHORT).show();
                        clickOrderCancle(order.getId());

                        dialog.dismiss();
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

//                new FirstAdapter(mContext,orderList);


            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailOrderH.class);
                intent.putExtra("idOrder",order.getId());
                mContext.startActivity(intent);
//                System.out.println(order.getId());
            }
        });
    }

    private void clickOrderAccept(int idOrder) {
        System.out.println(idOrder);
        try {
            OrderApi.AcceptOrder(mContext.getApplicationContext(), BASE_URL.BASE_ADMIN_URL + "accept-ordert/"+ idOrder, new VolleyCallback1() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(mContext, "Xoathanhcong", Toast.LENGTH_SHORT).show();
                    isActive = true;
                    Intent intent = new Intent(mContext, Admin.class);
                    mContext.startActivity(intent);
//                    orderList.notifyDataSetChanged();
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(mContext,  "khongthanhcong", Toast.LENGTH_SHORT).show();
                    System.out.println(errorMessage);
                }


            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void clickOrderCancle(int idOrder) {
        System.out.println(idOrder);
        try {
            OrderApi.CancleOrder(mContext.getApplicationContext(), BASE_URL.BASE_ADMIN_URL + "accept-orderf/"+ idOrder, new VolleyCallback1() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Toast.makeText(mContext, "Xoathanhcong", Toast.LENGTH_SHORT).show();
                    isActive = true;
                    Intent intent = new Intent(mContext, Admin.class);
                    mContext.startActivity(intent);
//                    orderList.notifyDataSetChanged();
                }

                @Override
                public void onError(VolleyError errorMessage) {
                    Toast.makeText(mContext,  "khongthanhcong", Toast.LENGTH_SHORT).show();
                    System.out.println(errorMessage);
                }


            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public int getItemCount () {
            return orderList.size();
        }

        public class ViewHolderAdmin extends RecyclerView.ViewHolder {

            private TextView diachi,idkhach,idorder;
            private ImageView ivAccept,ivCancle;
            private CardView cardView;

            public ViewHolderAdmin(@NonNull View itemView) {
                super(itemView);
                diachi = itemView.findViewById(R.id.addressOrder);
                idkhach = itemView.findViewById(R.id.useridOrder);
                idorder = itemView.findViewById(R.id.orderId);
                ivAccept = itemView.findViewById(R.id.accept);
                ivCancle = itemView.findViewById(R.id.cancle);
                cardView = itemView.findViewById(R.id.card_order);
            }

        }
    }



