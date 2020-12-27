package com.example.dailynews.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dailynews.Fragment.BusinessFragment;
import com.example.dailynews.Fragment.EnterFragment;
import com.example.dailynews.Fragment.HealthFragment;
import com.example.dailynews.Fragment.MainFragment;
import com.example.dailynews.Fragment.SportsFragment;
import com.example.dailynews.Fragment.TechnologyFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new BusinessFragment();
            case 2:
                return new EnterFragment();
            case 3:
                return new HealthFragment();
            case 4:
                return new SportsFragment();
            case 5:
                return new TechnologyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
}
