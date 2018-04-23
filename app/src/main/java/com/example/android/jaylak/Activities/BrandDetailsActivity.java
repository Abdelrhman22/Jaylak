package com.example.android.jaylak.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.jaylak.Adapters.BrandCategoryAdapter;
import com.example.android.jaylak.Adapters.BrandFeaturesAdapter;
import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.Models.BrandCategory;
import com.example.android.jaylak.Models.BrandFeature;
import com.example.android.jaylak.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BrandDetailsActivity extends AppCompatActivity {

    private ArrayList<BrandCategory> brandCategoryArrayList = new ArrayList<>();
    private ArrayList<BrandFeature> brandFeatureArrayList = new ArrayList<>();

    private TextView brandNameTV, brandLocationTV;
    private ImageView brandImage;
    private RecyclerView brandFeatureRecyclerView, brandCategoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        brandNameTV = findViewById(R.id.tv_brand_name);
        brandLocationTV = findViewById(R.id.tv_brand_location);
        brandImage = findViewById(R.id.iv_brand_image);

        brandFeatureRecyclerView = findViewById(R.id.recycler_view_brand_features);
        brandCategoryRecyclerView = findViewById(R.id.recycler_view_brand_category);

        brandFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        brandCategoryRecyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();
        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.getBrandDetails("Bearer XndlEmX6GgiVb370bJDQQioBYQbW6Rf6WICnXB1VZibMBp4tS0pwUxaMuKJx", 34).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response.body().string()).optJSONObject("data");
                    Log.i("Brand : ", jsonObject.toString());

                    brandNameTV.setText(jsonObject.getString("name"));
                    brandLocationTV.setText(jsonObject.getString("address"));
                    Picasso.get().load(jsonObject.getString("ximage")).into(brandImage);

                    JSONArray featuresJsonArray = jsonObject.getJSONArray("features");
                    JSONArray categoriesJsonArray = jsonObject.getJSONArray("categories");

                    BrandFeature brandFeature;
                    BrandCategory brandCategory;

                    for (int i = 0; i < featuresJsonArray.length(); i++) {

                        JSONObject brand = featuresJsonArray.getJSONObject(i);

                        String name = brand.getString("name");
                        String image = brand.getString("ximage");

                        brandFeature = new BrandFeature(name, image);

                        brandFeatureArrayList.add(brandFeature);
                    }
                    BrandFeaturesAdapter brandFeaturesAdapter = new BrandFeaturesAdapter(brandFeatureArrayList, getApplicationContext());
                    brandFeatureRecyclerView.setAdapter(brandFeaturesAdapter);

                    for (int i = 0; i < categoriesJsonArray.length(); i++) {

                        JSONObject brand = categoriesJsonArray.getJSONObject(i);

                        String name = brand.getString("name");
                        String description = brand.getString("description");
                        String image = brand.getString("ximage");

                        brandCategory = new BrandCategory(name,description, image);

                        brandCategoryArrayList.add(brandCategory);
                    }
                    BrandCategoryAdapter brandCategoryAdapter = new BrandCategoryAdapter(brandCategoryArrayList, getApplicationContext());
                    brandCategoryRecyclerView.setAdapter(brandCategoryAdapter);

  //                  Log.v("Feature : ", String.valueOf(brandFeatureArrayList.size()));
//                    Log.v("Category : ", String.valueOf(brandCategoryArrayList.get(1).getName()));


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.brand_rate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_rate:
                Toast.makeText(getApplicationContext(), "Rate", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_location:
                Toast.makeText(getApplicationContext(), "Location", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}