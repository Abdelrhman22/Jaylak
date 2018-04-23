package com.example.android.jaylak.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private EditText fullNameET, emailET, passwordET, mobileET;
    private TextView signInTVButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signInTVButton = findViewById(R.id.signin_tv_button);

        fullNameET = findViewById(R.id.et_full_name);
        emailET = findViewById(R.id.et_email);
        passwordET = findViewById(R.id.et_password);
        mobileET = findViewById(R.id.et_password);

        registerButton = findViewById(R.id.btn_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName, email, password, mobile;

                fullName = fullNameET.getText().toString().trim();
                email = emailET.getText().toString().trim();
                password = passwordET.getText().toString().trim();
                mobile = mobileET.getText().toString().trim();

                if (!fullName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !mobile.isEmpty()) {
                    register(fullName, email, mobile, password);
                } else {
                    fullNameET.setError("Required");
                    emailET.setError("Required");
                    passwordET.setError("Required");
                    mobileET.setError("Required");
                }


            }
        });
        signInTVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signUpIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(signUpIntent);
            }
        });
    }

    private void register(String username, String email, String password, String mobile) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();

        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.userRegister(username, email, mobile, password, password).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(), "Register Done", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Check Mail for verification", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_LONG).show();
                    emailET.setError("Wrong/Used Email Address");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}