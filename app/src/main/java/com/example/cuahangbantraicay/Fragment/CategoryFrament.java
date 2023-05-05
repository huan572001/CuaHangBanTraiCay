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

import com.example.cuahangbantraicay.API.HomeAPI;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.VolleyCallback;
import com.example.cuahangbantraicay.adapter.ProductAdapter;
import com.example.cuahangbantraicay.adapter.TypeProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryFrament extends Fragment {
    TypeProductAdapter typeProductAdapter;
    private RecyclerView rcvTypeProduct;
    List<Category> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_type, container, false);
        setControl(view);
        createViewTypeProduct();;
        return view;
    }

    private void createViewTypeProduct() {
        typeProductAdapter = new TypeProductAdapter(getContext(),"2");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvTypeProduct.setLayoutManager(linearLayoutManager);
        try {
            getListTypeProduct();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        rcvTypeProduct.setAdapter(typeProductAdapter);

    }


    private void setControl(View view) {
        rcvTypeProduct = view.findViewById(R.id.rcv_type
        );

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
