package com.example.cuahangbantraicay.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.activity.DetailsProduct;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHoder> {
    private Context mContext;
    private List<Product> ListProduct;

    public ProductAdapter(Context mContext) {
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
        System.out.println(product.getDiscout()+"===============================");
        if (product == null) return;
        holder.nameProduct.setText(product.getName());
        holder.price.setText("giá "+String.valueOf(product.getPrice_sell()));
        holder.discount.setText(String.valueOf(product.getDiscout())+"%");
        holder.quantity_sold.setText("Đã bán "+(String.valueOf(product.getQuantity_sold())));
        System.out.println(product.getImage());
        Glide.with(mContext).load(product.getImage()).into(holder.imgProduct);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsProduct.class);
                intent.putExtra("idProduct",product.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ListProduct != null) return ListProduct.size();
        return 0;
    }

    public class ProductViewHoder extends RecyclerView.ViewHolder {
        private TextView nameProduct, price, discount, quantity_sold;
        private ImageView imgProduct;
        private CardView cardView;
        public ProductViewHoder(@NonNull View itemView) {
            super(itemView);
            cardView= itemView.findViewById(R.id.cardTypeProduct);
            imgProduct = itemView.findViewById(R.id.img_product_type);
            nameProduct = itemView.findViewById(R.id.tv_name_product);
            price = itemView.findViewById(R.id.tv_price);
            discount = itemView.findViewById(R.id.tv_discount);
            quantity_sold = itemView.findViewById(R.id.tv_quantity_sold);

        }
    }
}
