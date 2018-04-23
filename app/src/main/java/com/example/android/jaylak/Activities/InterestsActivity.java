package com.example.android.jaylak.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.jaylak.Models.Interest;
import com.example.android.jaylak.Adapters.InterestsAdapter;
import com.example.android.jaylak.R;

import java.util.ArrayList;

public class InterestsActivity extends AppCompatActivity {

    private InterestsAdapter interestsAdapter;
    private GridView gv;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        gv = (GridView) findViewById(R.id.gv_interests);
        searchButton = findViewById(R.id.btn_search);

        interestsAdapter = new InterestsAdapter(getApplicationContext(), getData());
        gv.setAdapter(interestsAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(InterestsActivity.this, QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private ArrayList getData() {

        ArrayList<Interest> interestArrayList = new ArrayList<>();

        Interest interest;

        interest = new Interest("Cafes", getResources().getDrawable(R.drawable.cafes));
        interestArrayList.add(interest);


        interest = new Interest("Shopping", getResources().getDrawable(R.drawable.shopping));
        interestArrayList.add(interest);


        interest = new Interest("Beauty", getResources().getDrawable(R.drawable.beauty));
        interestArrayList.add(interest);


        interest = new Interest("Car Service", getResources().getDrawable(R.drawable.carservice));
        interestArrayList.add(interest);


        interest = new Interest("Software", getResources().getDrawable(R.drawable.software));
        interestArrayList.add(interest);


        return interestArrayList;
    }
}