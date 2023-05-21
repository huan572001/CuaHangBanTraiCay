package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.AdminOrderAdapter;
import com.example.cuahangbantraicay.adapter.TabEvaluateAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TabLayout tabLayout;
    ViewPager viewPager;


    public AdminOrderFragment() {
        // Required empty public constructor
    }


    public static AdminOrderFragment newInstance(String param1, String param2) {
        AdminOrderFragment fragment = new AdminOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_admin, container, false);
        setControl(view);
        onCreateTab();
        return view;
    }

    private void setControl(View view) {
        tabLayout = view.findViewById(R.id.tabAdmin);
        viewPager=view.findViewById(R.id.viewAdmin);
    }

    private void onCreateTab() {
//        TabEvaluateAdapter adapter = new TabEvaluateAdapter(getContext(), getActivity().getSupportFragmentManager());
        AdminOrderAdapter adapter = new AdminOrderAdapter(getContext(), getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }



}