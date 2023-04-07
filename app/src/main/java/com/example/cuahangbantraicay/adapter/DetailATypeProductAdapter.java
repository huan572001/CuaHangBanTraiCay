package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;

import java.util.List;

public class DetailATypeProductAdapter extends RecyclerView.Adapter<DetailATypeProductAdapter.ProductViewHoder> {
    private Context mContext;
    private List<Product> ListProduct;
    public DetailATypeProductAdapter(Context mContext){
        this.mContext=mContext;

    }
    public void setData(List<Product> list) {

        this.ListProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.child_detail_a_type_product,parent,false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoder holder, int position) {
            Product product=ListProduct.get(position);
            if(product==null)return;
            holder.nameProduct.setText(product.getName());

    }

    @Override
    public int getItemCount() {
        if(ListProduct!=null) return ListProduct.size();
        return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder  {
        private TextView nameProduct;
        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            nameProduct=itemView.findViewById(R.id.txtNameProduct);
        }
    }
}
