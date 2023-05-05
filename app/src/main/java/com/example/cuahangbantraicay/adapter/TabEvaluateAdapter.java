package com.example.cuahangbantraicay.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cuahangbantraicay.Fragment.HaveEvaluatedFragment;
import com.example.cuahangbantraicay.Fragment.WaitingForReviewFragment;
import com.example.cuahangbantraicay.R;

public class TabEvaluateAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public TabEvaluateAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WaitingForReviewFragment();
            case 1:
                return new HaveEvaluatedFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Chờ đánh giá";
            case 1:
                return "Đã đánh giá";
            default:
                return null;
        }
    }
}
