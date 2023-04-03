package com.example.cuahangbantraicay.Fragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.ProductAdapter;
import com.example.cuahangbantraicay.adapter.TypeProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomeProduct extends Fragment {
    private RecyclerView rcvNewPro,rcvPopulerProduct,rcvTypeProduct;
    ProductAdapter newProductAdapter,populerProductAdapter;
    TypeProductAdapter typeProductAdapter;
    private  void setControl(View view) {
        rcvNewPro = view.findViewById(R.id.rcv_new_product);
        rcvPopulerProduct=view.findViewById(R.id.rcv_populer_product);
        rcvTypeProduct=view.findViewById(R.id.rcv_type_product);
    }
    private void createViewTypeProduct(){
        typeProductAdapter= new TypeProductAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvTypeProduct.setLayoutManager(linearLayoutManager);
        typeProductAdapter.setData(getListTypeProduct());
        rcvTypeProduct.setAdapter(typeProductAdapter);

    }
    private void createViewNewProduct(){
        newProductAdapter = new ProductAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvNewPro.setLayoutManager(linearLayoutManager);
        newProductAdapter.setData(getListNewProduct());
        rcvNewPro.setAdapter(newProductAdapter);
    }
    private void createViewPopulerProduct(){
        populerProductAdapter = new ProductAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcvPopulerProduct.setLayoutManager(linearLayoutManager);
        populerProductAdapter.setData(getListNewProduct());
        rcvPopulerProduct.setAdapter(populerProductAdapter);
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

    private List<Product> getListNewProduct() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(R.drawable.logo, "username1"));
        list.add(new Product(R.drawable.logo, "username2"));
        list.add(new Product(R.drawable.logo, "username3"));
        list.add(new Product(R.drawable.logo, "username4"));
        list.add(new Product(R.drawable.logo, "username5"));
        list.add(new Product(R.drawable.logo, "username6"));
        return list;
    }
    private List<String> getListTypeProduct() {
        List<String> list = new ArrayList<>();
        list.add("username1");
        list.add("username2");
        list.add("username3");
        list.add("username4");
        list.add("username5");
        list.add( "username6");
        return list;
    }
}
