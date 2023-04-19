package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cuahangbantraicay.R;


public class PaymentFragment extends Fragment {
    TextView btnPay;
    String address;
    public PaymentFragment(String address){
        this.address= address;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_payment, container, false);
        btnPay=view.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new ConfirmFragment(address));
                ft.commit();
            }
        });
        return view;
    }
}
