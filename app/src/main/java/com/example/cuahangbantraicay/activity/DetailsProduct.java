package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;

public class DetailsProduct extends AppCompatActivity {
    Product product;
    ImageView img;
    TextView tv_name,tv_describe;
    private  void setControl(){
        tv_name=findViewById(R.id.tv_name_priduct);
        tv_describe=findViewById(R.id.tv_describe_product);
        img=findViewById(R.id.img_product);
    }
    private void onDataProduct(){
        product= (Product) getIntent().getSerializableExtra("a");
        img.setImageResource(product.getResourceId());
        tv_name.setText(product.getName().toString());
        tv_describe.setText(product.getName().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        setControl();
        onDataProduct();

    }
}