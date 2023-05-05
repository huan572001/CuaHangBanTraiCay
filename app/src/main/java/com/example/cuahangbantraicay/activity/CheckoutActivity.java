package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Fragment.AddressFragment;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        getSupportActionBar().hide();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.content_frame, new AddressFragment());
        ft.commit();
    }
}