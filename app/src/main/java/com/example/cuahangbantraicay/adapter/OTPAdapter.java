package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;

import java.util.List;


public class
OTPAdapter extends RecyclerView.Adapter<OTPAdapter.OTPViewHoder>{
    private List<String> listOTP;
    private Context mContext;
    private OnItemClickListener mListener;//tạo đối tượng onclick để lấy ra giá trị

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public OTPAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<String> list) {

        this.listOTP = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OTPViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_otp, parent, false);
        return new OTPViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OTPViewHoder holder, int position) {
        String item=listOTP.get(position);
        holder.btn_Number.setText(item);
        //chiều dài lớn hơn 6 là rcv bảng sô
        if (getItemCount()>6){
            holder.btn_Number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(item);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (listOTP != null) return listOTP.size();
        return 0;
    }
    public class OTPViewHoder extends RecyclerView.ViewHolder {
        private Button btn_Number;
        public OTPViewHoder(@NonNull View itemView) {
            super(itemView);
            btn_Number=itemView.findViewById(R.id.btn_number);
        }
    }

    // tạo interface để lấy được giá trị onclick của adapter
    public interface OnItemClickListener {
        void onItemClick(String value);
    }
}
