package com.android.qin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mLaunchViewPager;
    BottomNavigationView navigation_launch;
    MenuItem menuItem;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mLaunchViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mLaunchViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mLaunchViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mLaunchViewPager = findViewById(R.id.viewpager_launch);
            navigation_launch = findViewById(R.id.navigation_launch);
            setNavigation();
        }


        private void setNavigation() {
            navigation_launch.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            mLaunchViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    if (menuItem != null) {
                        menuItem.setChecked(false);
                    } else {
                        navigation_launch.getMenu().getItem(0).setChecked(false);
                    }
                    menuItem = navigation_launch.getMenu().getItem(position);
                    menuItem.setChecked(true);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            List<Fragment> list_fragment = new ArrayList<>();
            list_fragment.add(new OneFragment());
            list_fragment.add(new TwoFragment());
            list_fragment.add(new ThreeFragment());
            BottomViewAdapter adapter = new BottomViewAdapter(getSupportFragmentManager(), list_fragment);
            mLaunchViewPager.setAdapter(adapter);
            mLaunchViewPager.setOffscreenPageLimit(3);//设置缓存view 的个数
        }
    }
