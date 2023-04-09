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
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;


import java.util.List;

public class DetailATypeProductAdapter extends RecyclerView.Adapter<DetailATypeProductAdapter.ProductViewHoder> {
    private Context mContext;
    private List<Product> ListProduct;

    public DetailATypeProductAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void setData(List<Product> list) {

        this.ListProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_detail_a_type_product, parent, false);
        return new ProductViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHoder holder, int position) {
        Product product = ListProduct.get(position);
        if (product == null) return;
        holder.nameProduct.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice_sell()));
        holder.discount.setText(String.valueOf(product.getDiscout()));
        holder.quantity_sold.setText(String.valueOf(product.getQuantity_sold()));
        System.out.println(product.getImage());
        String url="https://cdn.tgdd.vn/Files/2020/06/08/1261696/moi-tai-bo-hinh-nen-asus-rog-2020-moi-nhat_800x450.jpg";
        Glide.with(mContext).load(url).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        if (ListProduct != null) return ListProduct.size();
        return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder {
        private TextView nameProduct, price, discount, quantity_sold;
        private ImageView imgProduct;

        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product_type);
            nameProduct = itemView.findViewById(R.id.tv_name_product);
            price = itemView.findViewById(R.id.tv_price);
            discount = itemView.findViewById(R.id.tv_discount);
            quantity_sold = itemView.findViewById(R.id.tv_quantity_sold);

        }
    }
}
