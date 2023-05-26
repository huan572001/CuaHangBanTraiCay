package com.example.cuahangbantraicay.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Fragment.OrderItemFragment;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    ArrayList<Order> listOrder;

    public  OrderAdapter(ArrayList<Order> listOrder,Context context){
        this.listOrder=listOrder;
        this.context=context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(listOrder.get(position).getStatus()==false){
            holder.txtStatus.setText("Pending");
        }else{
            holder.txtStatus.setText("Success");
        }

        holder.txtMDH.setText(String.valueOf(listOrder.get(position).getId()));
        holder.txtDate.setText(String.valueOf(listOrder.get(position).getCreatedAt()));
        holder.txtAddress.setText(listOrder.get(position).getAddress());


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frameOrder, new OrderItemFragment(Integer.valueOf(listOrder.get(position).getId())));
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtStatus,txtDate,txtAddress,txtMDH,txtCountOrder;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            txtMDH=itemView.findViewById(R.id.txtMDH);
            txtCountOrder=itemView.findViewById(R.id.txtCountOrder);
            mainLayout=itemView.findViewById(R.id.statisticLayout);

        }
    }
}
