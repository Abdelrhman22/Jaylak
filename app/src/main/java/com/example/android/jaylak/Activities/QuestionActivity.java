package com.example.android.jaylak.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.jaylak.R;

public class QuestionActivity extends AppCompatActivity {

    private Button questionSearchButton;
    private View questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final ImageView image = findViewById(R.id.question_background);
        questionSearchButton = findViewById(R.id.btn_question_search);
        questionList = findViewById(R.id.question_list_search);

        questionSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QuestionActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });

        questionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionListSearch();
            }
        });


        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);

        image.startAnimation(animation);

        animation.setRepeatCount(Animation.INFINITE);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {

                image.setBackground(getResources().getDrawable(R.drawable.carservice));
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

                int max = 4, min = 1;
                int randNum = min + (int) (Math.random() * ((max - min) + 1));

                if (randNum == 1)
                    image.setImageDrawable(getResources().getDrawable(R.drawable.software));
                else if (randNum == 2)
                    image.setImageDrawable(getResources().getDrawable(R.drawable.question_background_1));
                else if (randNum == 3)
                    image.setImageDrawable(getResources().getDrawable(R.drawable.question_background_2));
                else if (randNum == 4)
                    image.setImageDrawable(getResources().getDrawable(R.drawable.question_background_3));
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
            }
        });
    }

    public void questionListSearch() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.question_list, null);

        dialogBuilder.setView(dialogView);
        dialogBuilder.setMessage(" ");


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

        AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();
    }
}