package com.example.online_shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class SimplePagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_pager);

//        Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();

        ViewPager Pager=(ViewPager) findViewById(R.id.pager);
        Pager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_pager);
        tabLayout.setupWithViewPager(Pager);

//        Pager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));
//

    }
}