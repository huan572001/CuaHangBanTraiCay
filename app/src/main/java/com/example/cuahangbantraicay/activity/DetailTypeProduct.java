package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.cuahangbantraicay.API.ProductAPI;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.DetailATypeProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailTypeProduct extends AppCompatActivity {
    int id;
    RecyclerView rcvProduct;
    DetailATypeProductAdapter detailATypeProductAdapter;
    List<Product> listProduct= new ArrayList<>();
    private void setControl() {
        rcvProduct = findViewById(R.id.rcv_detail_a_type_product);
    }

    private void createViewProduct() {
        detailATypeProductAdapter=new DetailATypeProductAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvProduct.setLayoutManager(linearLayoutManager);
        getListDetailATypePoduct();
        rcvProduct.setAdapter(detailATypeProductAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_detail_a_type);
        setControl();
        id = (int) getIntent().getSerializableExtra("idCategory");
        createViewProduct();

    }

    private void getListDetailATypePoduct() {
        try {
            ProductAPI.getProductByIDCategory(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    System.out.println(result+"=====================");

                    try {
                        if ((Boolean) result.get("success")) {

                            JSONArray events = result.getJSONArray("data");
                            for (int j = 0; j < events.length(); j++) {
                                Product item = new Product((Integer) events.getJSONObject(j).get("id"), (String) events.getJSONObject(j).get("name"));
                                listProduct.add(item);
                            }
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