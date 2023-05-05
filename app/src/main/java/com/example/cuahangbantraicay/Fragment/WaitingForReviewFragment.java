package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.ProductAdapter;
import com.example.cuahangbantraicay.adapter.WaitingForReviewAdapter;

import java.util.ArrayList;
import java.util.List;


public class WaitingForReviewFragment extends Fragment  {

    RecyclerView rcv_waitingForReview;
    WaitingForReviewAdapter waitingForReviewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_waiting_for_review, container, false);
        setControl(view);
        createViewWaitingForReview();
        return view;
    }

    private void setControl(View view) {
        rcv_waitingForReview=view.findViewById(R.id.rcv_waitingForReview);
    }
    private void createViewWaitingForReview() {
        waitingForReviewAdapter = new WaitingForReviewAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_waitingForReview.setLayoutManager(linearLayoutManager);
        getListProduct();
        rcv_waitingForReview.setAdapter(waitingForReviewAdapter);
    }

    private void getListProduct() {
        List<WaitingForReview> listNewProduct=new ArrayList<>();
        listNewProduct.add(new WaitingForReview(1,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        listNewProduct.add(new WaitingForReview(2,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        listNewProduct.add(new WaitingForReview(3,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        listNewProduct.add(new WaitingForReview(4,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        listNewProduct.add(new WaitingForReview(5,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        listNewProduct.add(new WaitingForReview(6,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        listNewProduct.add(new WaitingForReview(7,"https://znews-photo.zingcdn.me/w660/Uploaded/mdf_eioxrd/2021_07_06/2.jpg","sản phẩm này rất tôt hay mua sản phảm này",60,2));
        waitingForReviewAdapter.setData(listNewProduct);
    }
}