package com.example.android.jaylak.Activities;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.jaylak.Adapters.BrandAdapter;
import com.example.android.jaylak.Fragments.AllBrandsFragment;
import com.example.android.jaylak.Fragments.NearbyBrandsFragment;
import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.Models.Brand;
import com.example.android.jaylak.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BrandsActivity extends AppCompatActivity {

    private BrandsFragmentPagerAdapter adapter;
    private ViewPager viewPager;
    private SearchView searchView;
    private BrandAdapter brandAdapter;
    private NearbyBrandsFragment nearbyBrandsFragment;
    private AllBrandsFragment allBrandsFragment;

    String API_TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);

        brandAdapter = new BrandAdapter(getApplicationContext(), new ArrayList<Brand>());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.pager);
        adapter = new BrandsFragmentPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

//        API_TOKEN = getIntent().getExtras().getString("API_TOKEN");


//        Intent intent = new Intent(getApplicationContext(), StoreReservationActivity.class);
//        startActivity(intent);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                Toast.makeText(getApplicationContext(), "Selected page position: " + position, Toast.LENGTH_SHORT).show();
//
//                if (searchView != null && !searchView.isIconified()) {
//
//                    //  searchView.onActionViewExpanded();
//                    searchView.setIconified(true);
//                    searchView.setIconified(true);
//                }
//            }
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                Toast.makeText(getApplicationContext(), "onPageScrolled", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//                Toast.makeText(getApplicationContext(), "onPageScrollStateChanged", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.brand_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("Search");

        if (menuItem != null) {
            searchView = (SearchView) menuItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }

        searchView.setIconifiedByDefault(true);

        MenuItemCompat.expandActionView(menuItem);

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                FragmentPagerAdapter pagerAdapter = (FragmentPagerAdapter) viewPager.getAdapter();

                for (int i = 0; i < pagerAdapter.getCount(); i++) {

                    Fragment viewPagerFragment = (Fragment) viewPager.getAdapter().instantiateItem(viewPager, i);

                    if (viewPagerFragment != null && viewPagerFragment.isAdded()) {

                        if (viewPagerFragment instanceof NearbyBrandsFragment) {
                            nearbyBrandsFragment = (NearbyBrandsFragment) viewPagerFragment;
                            if (nearbyBrandsFragment != null) {
                                nearbyBrandsFragment.beginSearch(newText);
                            }
                        } else if (viewPagerFragment instanceof AllBrandsFragment) {
                            allBrandsFragment = (AllBrandsFragment) viewPagerFragment;
                            if (allBrandsFragment != null) {
                                allBrandsFragment.beginSearch(newText);
                            }
                        }
                    }
                }
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_filter:
                filterBrandsResult();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void filterBrandsResult() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.popup_layout, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Search Filter");

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });

        AlertDialog b = dialogBuilder.create();

        b.show();
    }

    private void getBrands() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();

        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.brands().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(getApplicationContext(), "Login Done" + "\n" + response.body() , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public class BrandsFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public BrandsFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position) {
                case 0:
                    NearbyBrandsFragment tab1 = new NearbyBrandsFragment();
                    return tab1;
                case 1:
                    AllBrandsFragment tab2 = new AllBrandsFragment();
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
                    return mContext.getString(R.string.tab1_nearby_brands);
                case 1:
                    return mContext.getString(R.string.tab2_all_brands);
                default:
                    return null;
            }
        }
    }
}