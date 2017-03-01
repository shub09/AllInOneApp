package com.example.user.allinoneapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.user.allinoneapp.Adapter.ViewPagerAdapter;
import com.example.user.allinoneapp.Fragments.Fragment1;
import com.example.user.allinoneapp.Fragments.Fragment2;
import com.example.user.allinoneapp.Fragments.Fragment3;
import com.example.user.allinoneapp.Fragments.Fragment4;
import com.example.user.allinoneapp.Fragments.Fragment5;
import com.example.user.allinoneapp.Fragments.Fragment6;
import com.example.user.allinoneapp.R;

/**
 * Created by user on 2/18/2017.
 */

public class ViewPagerModule extends AppCompatActivity{

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_module);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Fragment1() , "ONE");
        adapter.addFragment(new Fragment2(), "TWO");
        adapter.addFragment(new Fragment3(), "THREE");
        adapter.addFragment(new Fragment4() , "FOUR");
        adapter.addFragment(new Fragment5(), "FIVE");
        adapter.addFragment(new Fragment6(), "SIX");
        viewPager.setAdapter(adapter);
    }
}
