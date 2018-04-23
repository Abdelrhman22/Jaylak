package com.example.android.jaylak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.Models.Brand;
import com.example.android.jaylak.Adapters.BrandAdapter;
import com.example.android.jaylak.R;
import com.facebook.FacebookSdk;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NearbyBrandsFragment extends Fragment {

    private ArrayList<Brand> brandArrayList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private BrandAdapter brandAdapter;

    public NearbyBrandsFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_nearby, container, false);

        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) view.findViewById(R.id.brand_nearby_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        brandAdapter = new BrandAdapter(getContext(), getData());
        recyclerView.setAdapter(brandAdapter);

        getData();

        brandAdapter.notifyDataSetChanged();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();

        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.getBrands("XndlEmX6GgiVb370bJDQQioBYQbW6Rf6WICnXB1VZibMBp4tS0pwUxaMuKJx",20).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Toast.makeText(getContext(), response.body().string(), Toast.LENGTH_LONG).show();
                    Log.v("Token", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return view;
    }

    private ArrayList getData() {

        Brand brand;

        brand = new Brand("HM", "Cafe", 5, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Shop", 4, getResources().getDrawable(R.drawable.shopping));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Restaurants", 3, getResources().getDrawable(R.drawable.beauty));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Club", 2, getResources().getDrawable(R.drawable.carservice));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Clothes", 1, getResources().getDrawable(R.drawable.software));
        brandArrayList.add(brand);

        brand = new Brand("HM", "Cafe", 5, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Shop", 4, getResources().getDrawable(R.drawable.shopping));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Restaurants", 3, getResources().getDrawable(R.drawable.beauty));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Club", 2, getResources().getDrawable(R.drawable.carservice));
        brandArrayList.add(brand);


        brand = new Brand("HM", "Clothes", 1, getResources().getDrawable(R.drawable.software));
        brandArrayList.add(brand);


        return brandArrayList;
    }

    public void beginSearch(String query) {
        Log.e("QueryFragment", query);
        brandAdapter.getFilter().filter(query);
    }
}