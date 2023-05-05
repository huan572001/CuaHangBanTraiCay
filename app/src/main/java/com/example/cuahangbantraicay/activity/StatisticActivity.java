package com.example.cuahangbantraicay.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.cuahangbantraicay.Fragment.StatisticFragment;
import com.example.cuahangbantraicay.R;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.content_frame, new StatisticFragment());
        ft.commit();
    }
}