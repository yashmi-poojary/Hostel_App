package com.example.hostel_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class Menu extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private androidx.appcompat.widget.Toolbar toolbar;
    viewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewPager = (ViewPager) findViewById(R.id.viewMenu);
        tabLayout = (TabLayout) findViewById(R.id.tabMenu);
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Menu");
        viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(viewPagerAdapter);

    }
}