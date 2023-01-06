package com.edu.usth.finalappmobile;

import android.icu.lang.UProperty;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.io.File;

public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new FileFragment();
            case 2:
                return new VideoFragment();
            case 3:
                return new ImageFragment();
            case 4:
                return new ProfileFragment();
            default:
                return new  HomeFragment();

        }
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Home";
                break;
            case 1:
                title = "File";
                break;
            case 2:
                title = "Image";
                break;
            case 3:
                title = "Video";
                break;
            case 4:
                title = "Profile";
                break;
        }
        return title;
    }
}
