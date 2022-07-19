package com.example.hostel_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdapter extends FragmentPagerAdapter {
    public viewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Monday();
            case 1: return new Tuesday();
            case 2: return new Wednesday();
            case 3: return new Thursday();
            case 4: return new Friday();
            case 5: return new Saturday();
            case 6: return new Sunday();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:return "Mon";
            case 1:return "Tue";
            case 2:return "Wed";
            case 3:return "Thu";
            case 4:return "Fri";
            case 5:return "Sat";
            case 6:return "Sun";


        }
        return null;
    }
}
