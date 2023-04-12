package com.example.cuahangbantraicay.Fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.API.HomeAPI;
import com.example.cuahangbantraicay.API.ProductAPI;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.ProductAdapter;
import com.example.cuahangbantraicay.adapter.TypeProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomeProduct extends Fragment {
    private RecyclerView rcvNewPro, rcvPopulerProduct, rcvTypeProduct;
    ProductAdapter newProductAdapter, popularProductAdapter;
    TypeProductAdapter typeProductAdapter;
    List<Category> list = new ArrayList<>();

    private void setControl(View view) {
        rcvNewPro = view.findViewById(R.id.rcv_new_product);
        rcvPopulerProduct = view.findViewById(R.id.rcv_populer_product);
        rcvTypeProduct = view.findViewById(R.id.rcv_type_product);
    }

    private void createViewTypeProduct() {
        typeProductAdapter = new TypeProductAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvTypeProduct.setLayoutManager(linearLayoutManager);
        try {
            getListTypeProduct();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        rcvTypeProduct.setAdapter(typeProductAdapter);

    }

    private void createViewNewProduct() {
        newProductAdapter = new ProductAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvNewPro.setLayoutManager(linearLayoutManager);

        getListNewProduct();
        rcvNewPro.setAdapter(newProductAdapter);
    }

    private void createViewPopulerProduct() {
        popularProductAdapter = new ProductAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvPopulerProduct.setLayoutManager(linearLayoutManager);
        getListPopularProduct();
        rcvPopulerProduct.setAdapter(popularProductAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fillter_product, container, false);
        setControl(view);
        createViewNewProduct();
        createViewPopulerProduct();
        createViewTypeProduct();
        return view;
    }
    private void getListPopularProduct() {
        List<Product> listPopularProduct=new ArrayList<>();
        try {
            ProductAPI.getPopularProduct(getContext(), new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if ((Boolean) result.get("success")) {
                            JSONArray events = result.getJSONArray("data");
                            JSONObject object= new JSONObject();
                            for (int j = 0; j < events.length(); j++) {
                                object=(JSONObject) events.get(j);
                                Product product=new Product();
                                product.setId(object.getInt("id"));
                                product.setName(object.getString("name"));
                                product.setImage(object.getString("image"));
                                product.setPrice_sell((float) object.getDouble("price_sell"));
                                product.setStatus(object.getBoolean("status"));
                                product.setDiscout(object.getInt("discout"));

                                listPopularProduct.add(product);
                            }
                            popularProductAdapter.setData(listPopularProduct);
                        }
                    } catch (JSONException e) {
                        System.out.println(e + "lỗi nè");

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
    private void getListNewProduct() {
        List<Product> listNewProduct=new ArrayList<>();
        try {
            ProductAPI.getNewProduct(getContext(), new VolleyCallback() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        if ((Boolean) result.get("success")) {
                                JSONArray events = result.getJSONArray("data");
                                JSONObject object= new JSONObject();
                                for (int j = 0; j < events.length(); j++) {
                                    object=(JSONObject) events.get(j);
                                    Product product=new Product();
                                    product.setId(object.getInt("id"));
                                    product.setName(object.getString("name"));
                                    product.setImage(object.getString("image"));
                                    product.setPrice_sell((float) object.getDouble("price_sell"));
                                    product.setStatus(object.getBoolean("status"));
                                    product.setDiscout(object.getInt("discout"));

                                    listNewProduct.add(product);
                                }
                            newProductAdapter.setData(listNewProduct);
                        }
                    } catch (JSONException e) {
                        System.out.println(e + "lỗi nè");

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

    private void getListTypeProduct() throws JSONException {

        HomeAPI.getAllCategory(getContext(), new VolleyCallback() {


            @Override
            public void onSuccess(JSONObject result) {

                try {
                    if ((Boolean) result.get("success")) {

                        JSONArray events = result.getJSONArray("data");
                        for (int j = 0; j < events.length(); j++) {
                            Category item = new Category((Integer) events.getJSONObject(j).get("id"), (String) events.getJSONObject(j).get("name"));
                            list.add(item);
                        }
                        typeProductAdapter.setData(list);
                    }
                } catch (JSONException e) {
                    System.out.println(e + "lỗi nè");

                }
            }

            @Override
            public void onError(JSONObject errorMessage) {
                System.out.println(errorMessage + "lỗi nè");
            }
        });
    }
}
