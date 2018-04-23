package com.example.android.jaylak.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.jaylak.R;
import com.example.android.jaylak.Fragments.StoreProductFragment;
import com.example.android.jaylak.Fragments.StoreServicesFragment;

public class StoreActivity extends AppCompatActivity {

    private StoreFragmentPagerAdapter adapter;
    private ViewPager viewPager;
//    private BrandAdapter brandAdapter;
//    private StoreServicesFragment storeServicesFragment;
//    private StoreProductFragment storeProductFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        viewPager = findViewById(R.id.store_pager);
        adapter = new StoreFragmentPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.store_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class StoreFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public StoreFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    StoreServicesFragment tab1 = new StoreServicesFragment();
                    return tab1;
                case 1:
                    StoreProductFragment tab2 = new StoreProductFragment();
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
                    return "Services";
                case 1:
                    return "Products";
                default:
                    return null;
            }
        }
    }
}