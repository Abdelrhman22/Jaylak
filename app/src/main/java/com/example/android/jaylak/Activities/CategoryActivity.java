package com.example.android.jaylak.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.android.jaylak.Adapters.CategoryAdapter;
import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.Models.Category;
import com.example.android.jaylak.Models.CategoryItem;
import com.example.android.jaylak.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    private List<CategoryItem> categoryArrayList = new ArrayList<>();
    private ImageButton backButton;

    private CategoryAdapter categoryAdapter;

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        gridView = findViewById(R.id.gv_category);
        backButton = findViewById(R.id.back_button);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).addConverterFactory(GsonConverterFactory.create()).build();

        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.getCategories().enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {

                categoryArrayList.addAll(response.body().getData());
                Toast.makeText(getApplicationContext(), categoryArrayList.get(1).toString(), Toast.LENGTH_LONG).show();

                categoryAdapter = new CategoryAdapter(getApplicationContext(),  categoryArrayList);
                gridView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(CategoryActivity.this, BrandsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CategoryActivity.this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}