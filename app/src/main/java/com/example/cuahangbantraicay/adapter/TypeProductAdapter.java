package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.DangNhap;

import java.util.List;

public class TypeProductAdapter extends RecyclerView.Adapter<TypeProductAdapter.TypeProductViewHoder> {

    private Context mContext;
    private List<String> mListTypeProduct;

    public TypeProductAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<String> list) {
        this.mListTypeProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TypeProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_list_type_product, parent, false);
        return new TypeProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeProductViewHoder holder, int position) {
        String product = mListTypeProduct.get(position);
        if (product == null) return;
        holder.btn_type_product.setText(product);
        holder.btn_type_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DangNhap.class);
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mListTypeProduct!=null)return mListTypeProduct.size();
        return 0;
    }

    public class TypeProductViewHoder extends RecyclerView.ViewHolder {
        private Button btn_type_product;

        public TypeProductViewHoder(@NonNull View itemView) {
            super(itemView);
            btn_type_product=itemView.findViewById(R.id.btn_type_product);
        }
    }
}
