package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class PopularProductFragment extends Fragment {
    ProductAdapter popularProductAdapter;
    private RecyclerView rcvPopularPro;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_child_detail_a_type, container, false);
        setControl(view);
        createViewPopulerProduct();;
        return view;
    }

    private void createViewPopulerProduct() {
        popularProductAdapter = new ProductAdapter(getContext());
        GridLayoutManager layoutManagerGrid = new GridLayoutManager(getContext(), 2);

        rcvPopularPro.setLayoutManager(layoutManagerGrid);
        getListPopularProduct();
        rcvPopularPro.setAdapter(popularProductAdapter);
    }

    private void setControl(View view) {
        rcvPopularPro=view.findViewById(R.id.rcv_detail_a_type_product);
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
}
