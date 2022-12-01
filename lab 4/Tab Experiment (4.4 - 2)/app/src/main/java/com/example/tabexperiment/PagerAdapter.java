package com.example.tabexperiment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    int mNumOfTabs;

    public PagerAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle, int NumOfTabs) {
        super(fragmentManager, lifecycle);
        this.mNumOfTabs = NumOfTabs;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}
