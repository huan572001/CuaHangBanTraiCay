package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.API.EcaluateAPI;
import com.example.cuahangbantraicay.API.ProductAPI;
import com.example.cuahangbantraicay.Fragment.StarsFrament;
import com.example.cuahangbantraicay.Modal.Ecaluate;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.EcaluateAdapter;
import com.example.cuahangbantraicay.adapter.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailsProduct extends AppCompatActivity {
    int idProduct;
    ImageView img;
    TextView tv_name, tv_content, tv_quantity_sold, tv_price_product, tv_discout,tv_price_cell,tv_buy;

    CardView cardEcakuate;
    RecyclerView rcv_ecaluate;
    EcaluateAdapter ecaluateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        getProductById();
        createViewProduct();
        replaceFragment(new StarsFrament(3.5));
    }

    private void createViewProduct() {
        ecaluateAdapter = new EcaluateAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                , RecyclerView.VERTICAL, false);
        rcv_ecaluate.setLayoutManager(linearLayoutManager);

        getEcaluate();
        rcv_ecaluate.setAdapter(ecaluateAdapter);
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

    private void setControl() {
        img = findViewById(R.id.img_product_detail);
        tv_name = findViewById(R.id.tv_name_product_detail);
        tv_discout = findViewById(R.id.tv_discount_in_Detail);
        tv_content = findViewById(R.id.tv_content_product);
        tv_quantity_sold = findViewById(R.id.tv_quantity_sold);
        tv_price_product = findViewById(R.id.tv_price_product);
        tv_buy = findViewById(R.id.tv_buy);
        rcv_ecaluate = findViewById(R.id.rcv_Evaluate);
        cardEcakuate=findViewById(R.id.CardEcaluate);
        tv_price_cell=findViewById(R.id.tv_price_cell);
    }

    private void getProductById() {
        idProduct = (int) getIntent().getSerializableExtra("idProduct");

        try {
            ProductAPI.getProductByID(this, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if ((Boolean) result.get("success")) {
                            JSONObject data = result.getJSONObject("data");
                            Glide.with(getBaseContext()).load(data.getString("image")).into(img);
                            tv_name.setText(data.getString("name"));
                            tv_content.setText(data.getString("content"));
                            tv_discout.setText((String) String.valueOf(data.getInt("discout")) + "%");
                            tv_quantity_sold.setText("Đã bán: " + String.valueOf(data.getInt("quantity_sold")));
                            tv_price_product.setText(String.valueOf(data.getDouble("price_sell"))+" vnd");
                            tv_price_product.setPaintFlags(tv_price_product.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                            int priceCell= (int) (data.getDouble("price_sell")*(100-data.getInt("discout"))/100);
                            tv_price_cell.setText(String.valueOf(priceCell)+" vnd");
                            setTitle(data.getString("name").toUpperCase());
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            }, idProduct);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.stars, fragment);
        transaction.commit();
    }

    private void getEcaluate() {
        List<Ecaluate> list = new ArrayList<>();
        try {
            EcaluateAPI.getAllEcaluateByProductId(this, idProduct, new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if ((Boolean) result.get("success")) {
                            JSONArray events = result.getJSONArray("data");
                            JSONObject object = new JSONObject();
                            for (int j = 0; j < events.length(); j++) {
                                object = (JSONObject) events.get(j);
                                Ecaluate ecaluate = new Ecaluate();
                                ecaluate.setName(object.getString("username"));
                                ecaluate.setStars(object.getInt("stars"));
                                ecaluate.setImage(object.getString("image"));
                                ecaluate.setComment(object.getString("comment"));
                                String myDate = "2023-05-06";
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date date = sdf.parse(myDate);
                                    String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);

                                    ecaluate.setTime(formattedDate);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                list.add(ecaluate);
                            }

                            ecaluateAdapter.setData(list);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(JSONObject errorMessage) {

                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}