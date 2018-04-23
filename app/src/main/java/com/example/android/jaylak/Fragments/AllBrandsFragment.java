package com.example.android.jaylak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.jaylak.Adapters.BrandAdapter;
import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.Models.Brand;
import com.example.android.jaylak.R;
import com.facebook.FacebookSdk;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AllBrandsFragment extends Fragment {

    private ArrayList<Brand> brandArrayList = new ArrayList<>();
    SearchView searchView;

    String API_TOKEN;
    private BrandAdapter brandAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public AllBrandsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_brands, container, false);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.brand_all_recyclerview);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        brandAdapter = new BrandAdapter(getContext(), brandArrayList);
        recyclerView.setAdapter(brandAdapter);

        getData();

//        API_TOKEN = getActivity().getIntent().getExtras().getString("API_TOKEN");

        brandAdapter.notifyDataSetChanged();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();

        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.brands().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Toast.makeText(getApplicationContext(), "Login Done" + "\n" + jsonObject, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
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

        brand = new Brand("dfd", "Cafes", 1, getResources().getDrawable(R.drawable.cafes));
        brandArrayList.add(brand);

        return brandArrayList;
    }

    public void beginSearch(String query) {
        Log.e("QueryFragment", query);
        brandAdapter.getFilter().filter(query);
    }
}