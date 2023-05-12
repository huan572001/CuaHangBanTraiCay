package com.example.cuahangbantraicay.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cuahangbantraicay.Fragment.FirstFragment;
import com.example.cuahangbantraicay.Fragment.SecondFragment;
import com.example.cuahangbantraicay.Fragment.ThirdFragment;

public class AdminOrderAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public AdminOrderAdapter(Context context, FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }
    private static final int NUM_PAGES = 3;

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                System.out.println("tab1");
                return new FirstFragment();
            case 1:
                System.out.println("tab2");

                return new SecondFragment();
            case 2:
                System.out.println("tab3");

                return new ThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Yeu Cau";
            case 1:
                return "Xac Nhan";
            case 2:
                return "Da Huy";
            default:
                return null;
        }
    }
}
