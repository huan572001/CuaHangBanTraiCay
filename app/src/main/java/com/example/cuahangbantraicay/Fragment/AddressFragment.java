package com.example.cuahangbantraicay.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cuahangbantraicay.R;

public class AddressFragment extends Fragment{
    Context context;
    EditText edtAddress;
    TextView btnPayment;
    public AddressFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_address, container, false);
        btnPayment=v.findViewById(R.id.txtPayment);
        edtAddress=v.findViewById(R.id.edtAddress);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtAddress.getText()!=null){
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, new PaymentFragment(edtAddress.getText().toString()));
                ft.commit();
                };
            };
        });

        return v;
    }
}
