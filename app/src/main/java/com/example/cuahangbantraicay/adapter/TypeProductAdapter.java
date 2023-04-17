package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.DangNhap;
import com.example.cuahangbantraicay.activity.DetailTypeProduct;

import java.util.Calendar;
import java.util.List;

public class TypeProductAdapter extends RecyclerView.Adapter<TypeProductAdapter.TypeProductViewHoder> {

    private Context mContext;
    private List<Category> mListTypeProduct;
    String displaytType;

    public TypeProductAdapter(Context mContext, String displaytType) {
        this.mContext = mContext;
        this.displaytType = displaytType;
    }

    public void setData(List<Category> list) {

        this.mListTypeProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TypeProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (displaytType.equals("1")) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_list_type_product, parent, false);
            return new TypeProductViewHoder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_type, parent, false);
        return new TypeProductViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TypeProductViewHoder holder, int position) {
        Category product = mListTypeProduct.get(position);
        if (product == null) return;
        holder.name_product.setText(product.getName());
        holder.ll_type_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetailTypeProduct.class);
                intent.putExtra("idCategory", product.getId());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mListTypeProduct != null) return mListTypeProduct.size();
        return 0;
    }

    public class TypeProductViewHoder extends RecyclerView.ViewHolder {
        private LinearLayout ll_type_product;
        private TextView name_product;

        private ImageView img_type;
        public TypeProductViewHoder(@NonNull View itemView) {
            super(itemView);
            if (displaytType.equals("1")) {
                ll_type_product = itemView.findViewById(R.id.LL_type_Product);
                name_product = itemView.findViewById(R.id.tv_name_type_product);
            }else {
                ll_type_product=itemView.findViewById(R.id.ll_type);
                name_product=itemView.findViewById(R.id.tv_name_type);
                img_type=itemView.findViewById(R.id.img_type);
            }
        }
    }
}
