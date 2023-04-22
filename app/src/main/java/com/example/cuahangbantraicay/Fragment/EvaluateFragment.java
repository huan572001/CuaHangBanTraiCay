package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.TabEvaluateAdapter;
import com.google.android.material.tabs.TabLayout;


public class EvaluateFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        setControl(view);
        onCreateTab();
        return view;
    }

    private void onCreateTab() {
        TabEvaluateAdapter adapter = new TabEvaluateAdapter(getContext(), getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setControl(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
    }
}