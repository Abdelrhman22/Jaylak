package com.example.android.jaylak.Activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.jaylak.Models.Brand;
import com.example.android.jaylak.Adapters.BrandAdapter;
import com.example.android.jaylak.Fragments.OrderFragment;
import com.example.android.jaylak.R;
import com.example.android.jaylak.Fragments.ReservationFragment;

import java.util.ArrayList;

public class ProfileHistoryActivity extends AppCompatActivity {

    private ProfileActionPagerAdapter adapter;
    private ViewPager viewPager;
    private BrandAdapter brandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        brandAdapter = new BrandAdapter(getApplicationContext(), new ArrayList<Brand>());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        adapter = new ProfileActionPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class ProfileActionPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public ProfileActionPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    OrderFragment tab1 = new OrderFragment();
                    return tab1;
                case 1:
                    ReservationFragment tab2 = new ReservationFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        // This determines the number of tabs
        @Override
        public int getCount() {
            return 2;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            switch (position) {
                case 0:
                    return "Order History";
                case 1:
                    return "Reservation History";
                default:
                    return null;
            }
        }
    }
}