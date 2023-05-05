package com.example.cuahangbantraicay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.model.Order;
import com.example.cuahangbantraicay.model.Statistic;

import java.util.ArrayList;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder> {
    ArrayList<Order>listOrder=new ArrayList<>();
    ArrayList<Statistic>listStatistic=new ArrayList<>();
    Double profit = Double.valueOf(0);
    public StatisticAdapter(ArrayList<Order> listOrder, ArrayList<Statistic> listStatistic){
        this.listOrder=listOrder;
        this.listStatistic=listStatistic;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_statistic_all,parent,false);
       return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtStatus.setText(String.valueOf(listOrder.get(position).getStatus()));
        holder.txtMDH.setText(String.valueOf(listOrder.get(position).getId()));
        holder.txtDate.setText(String.valueOf(listOrder.get(position).getCreatedAt()));
        holder.txtAddress.setText(listOrder.get(position).getAddress());
        profit=Double.valueOf(listStatistic.get(position).getPriceSell()-listStatistic.get(position).getPriceIn());
        holder.txtProfit.setText(String.valueOf(profit));
        holder.txtPriceIn.setText(""+listStatistic.get(position).getPriceIn());
        holder.txtPriceOut.setText(""+listStatistic.get(position).getPriceSell());
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPriceIn,txtPriceOut,txtProfit,txtMDH,txtDate,txtAddress,txtStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            txtMDH=itemView.findViewById(R.id.txtMDH);
            txtPriceIn=itemView.findViewById(R.id.txtPriceIn);
            txtPriceOut=itemView.findViewById(R.id.txtPriceSell);
            txtProfit=itemView.findViewById(R.id.txtProfit);
        }
    }
}
