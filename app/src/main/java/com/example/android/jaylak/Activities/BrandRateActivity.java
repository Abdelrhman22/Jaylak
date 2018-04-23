package com.example.android.jaylak.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.jaylak.Adapters.BrandRateAdapter;
import com.example.android.jaylak.Adapters.RateAdapter;
import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.Models.Rate;
import com.example.android.jaylak.R;

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

public class BrandRateActivity extends AppCompatActivity {

    private ArrayList<Rate> rateArrayList = new ArrayList<>();
    private RecyclerView rateRecyclerView;
    private BrandRateAdapter rateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_rate);

        rateRecyclerView = findViewById(R.id.brand_rates_recycler_view);
        rateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rateAdapter = new BrandRateAdapter(getApplicationContext(), rateArrayList);
        rateRecyclerView.setAdapter(rateAdapter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();
        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.getBrandRateAble("Bearer XndlEmX6GgiVb370bJDQQioBYQbW6Rf6WICnXB1VZibMBp4tS0pwUxaMuKJx", 34).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Toast.makeText(getApplicationContext(), response.body().string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        jaylakAPI.getBrandRates("Bearer XndlEmX6GgiVb370bJDQQioBYQbW6Rf6WICnXB1VZibMBp4tS0pwUxaMuKJx", 34).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                JSONObject rateJsonObject = null;
                try {
                    rateJsonObject = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.i("Rate", rateJsonObject.toString());

                JSONArray rateJsonArray = rateJsonObject.optJSONArray("data");

         //       Toast.makeText(getApplicationContext(), rateJsonArray.toString(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i < rateJsonArray.length(); i++) {

                    JSONObject ratedJsonObject;
                    ratedJsonObject = rateJsonArray.optJSONObject(i);

                    String name, image;
                    int foodRate = 0, serviceRate = 0, locationRate = 0, qualityRate = 0;

                    name = ratedJsonObject.optJSONObject("user").optString("name");
                    image = ratedJsonObject.optJSONObject("user").optString("ximage");

                    JSONArray rateJA = ratedJsonObject.optJSONArray("rated");

                    for (int j = 0; j < rateJA.length(); j++) {

                        JSONObject singleRatedJsonObject;
                        singleRatedJsonObject = rateJA.optJSONObject(j);

                        String rate;

                        rate = singleRatedJsonObject.optJSONObject("rateable").optString("name");

                        if (rate.equals("Service")){
                            serviceRate = singleRatedJsonObject.optInt("rating");
                            Log.i("Service", String.valueOf(serviceRate));
                            continue;
                        }
                        if (rate.equals("Location")){
                            locationRate = singleRatedJsonObject.optInt("rating");
                            Log.i("Location", String.valueOf(locationRate));
                            continue;
                        }
                        if (rate.equals("Quality")){
                            qualityRate = singleRatedJsonObject.optInt("rating");
                            Log.i("Quality", String.valueOf(qualityRate));
                            continue;
                        }
                        if (rate.equals("Food")){
                            foodRate = singleRatedJsonObject.optInt("rating");
                            Log.i("Food", String.valueOf(foodRate));
                            continue;
                        }
                    }
                    rateArrayList.add(new Rate(image, name, foodRate, serviceRate, locationRate, qualityRate));
                }

                Toast.makeText(getApplicationContext(), String.valueOf(rateArrayList.size()), Toast.LENGTH_SHORT).show();
                Log.i("Rate", rateJsonArray.toString());
                rateAdapter = new BrandRateAdapter(getApplicationContext(), rateArrayList);
                rateRecyclerView.setAdapter(rateAdapter);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}