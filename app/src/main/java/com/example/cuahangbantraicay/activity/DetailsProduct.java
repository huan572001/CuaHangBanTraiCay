package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangbantraicay.API.ProductAPI;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class DetailsProduct extends AppCompatActivity {
    int idProduct;
    ImageView img;
    TextView tv_name,tv_content,tv_quantity_sold,tv_price_product,tv_discout;
    Button btn_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        getProductById();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private  void setControl(){
        img=findViewById(R.id.img_product_detail);
        tv_name=findViewById(R.id.tv_name_product_detail);
        tv_discout=findViewById(R.id.tv_discount_in_Detail);
        tv_content=findViewById(R.id.tv_content_product);
        tv_quantity_sold=findViewById(R.id.tv_quantity_sold);
        tv_price_product=findViewById(R.id.tv_price_product);
        btn_buy=findViewById(R.id.btn_buy);
    }
    private void getProductById(){
        idProduct= (int) getIntent().getSerializableExtra("idProduct");

        try {
            ProductAPI.getProductByID(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if((Boolean) result.get("success")){
                            JSONObject data= result.getJSONObject("data");
                            tv_name.setText(data.getString("name"));
                            tv_content.setText(data.getString("content"));
                            tv_discout.setText((String)String.valueOf(data.getInt("discout")) +"%");
                            tv_quantity_sold.setText("Đã bán: "+String.valueOf(data.getInt("quantity_sold")));
                            tv_price_product.setText(String.valueOf(data.getDouble("price_sell")));
                            setTitle(data.getString("name").toUpperCase());
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            },idProduct);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}