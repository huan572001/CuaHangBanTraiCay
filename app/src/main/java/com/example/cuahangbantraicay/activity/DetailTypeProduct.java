package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.cuahangbantraicay.API.ProductAPI;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailTypeProduct extends AppCompatActivity {
    int id;
    RecyclerView rcvProduct;
    ProductAdapter detailATypeProductAdapter;
    List<Product> listProduct = new ArrayList<>();

    private void setControl() {
        rcvProduct = findViewById(R.id.rcv_detail_a_type_product);
    }

    private void createViewProduct() {
        detailATypeProductAdapter = new ProductAdapter(this);
        GridLayoutManager layoutManagerGrid = new GridLayoutManager(this, 2);

        rcvProduct.setLayoutManager(layoutManagerGrid);

        getListDetailATypePoduct();
        rcvProduct.setAdapter(detailATypeProductAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_detail_a_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        id = (int) getIntent().getSerializableExtra("idCategory");
        createViewProduct();

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
    private void getListDetailATypePoduct() {
        try {
            ProductAPI.getProductByIDCategory(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {


                    try {
                        if ((Boolean) result.get("success")) {

                            JSONArray events = result.getJSONArray("data");
                            JSONObject object= new JSONObject();
                            JSONObject category= new JSONObject();
                            category=result.getJSONObject("category");
                            JSONObject nameCategory= new JSONObject();
                            for (int j = 0; j < events.length(); j++) {
                                object=(JSONObject) events.get(j);
                                Product product=new Product();
                                product.setId(object.getInt("id"));
                                product.setName(object.getString("name"));
                                product.setImage(object.getString("image"));
                                product.setPrice_sell((float) object.getDouble("price_sell"));
                                product.setStatus(object.getBoolean("status"));
                                product.setDiscout(object.getInt("discout"));
                                listProduct.add(product);
                            }

                            setTitle(category.getString("name"));
                            detailATypeProductAdapter.setData(listProduct);
                        }
                    } catch (JSONException e) {
                        System.out.println(e + "lỗi nè");

                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            }, id);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}