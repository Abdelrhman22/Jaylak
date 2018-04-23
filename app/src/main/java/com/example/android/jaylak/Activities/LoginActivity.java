package com.example.android.jaylak.Activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.jaylak.Interface.JaylakAPI;
import com.example.android.jaylak.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private EditText emailET, passwordET;
    private TextView forgotPasswordTVButton, singUpTVButton;

    String API_TOKEN;

    private Button signInButton, facebookCustomLoginButton, googleSignInCustomButton;

    private AccessToken facebookAccessToken;

    private LoginButton facebookLoginButton;
    private CallbackManager callbackManager;

    private String email, name, profilePicture, providerKey;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton googleSingInButton;

    public String mChuckData;

    private PackageInfo packageInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_login);

//        try {
//            packageInfo = getPackageManager().getPackageInfo("com.example.android.jaylak", PackageManager.GET_SIGNATURES);
//
//            for (Signature signature : packageInfo.signatures) {
//                MessageDigest messageDigest;
//                messageDigest = MessageDigest.getInstance("SHA");
//                messageDigest.update(signature.toByteArray());
//
//                String keyHash = new String(Base64.encode(messageDigest.digest(), 0));
//
//                Log.e("KeyHash", keyHash);
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//
        initElements();

        singUpTVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        facebookLoginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_friends"));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleSignInCustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 1);
            }

        });


        // login with facebook handling
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookAccessToken = AccessToken.getCurrentAccessToken();
//
//                Toast.makeText(getApplicationContext(), facebookAccessToken + "\n" + "User ID:  "
//                        + loginResult.getAccessToken().getUserId()
//                        + "\n" + "Auth Token: "
//                        + loginResult.getAccessToken().getToken(), Toast.LENGTH_SHORT).show();
//
//
//
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//
//                                Log.v("LoginActivity", response.toString());
//
//
//                                try {
//                                    profilePicture = object.getJSONObject("picture").getJSONObject("data").getString("url");
//                                    Log.v("LoginActivity", profilePicture);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                try {
//                                    email = object.getString("email");
//                                    Log.v("LoginActivity", email);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                try {
//                                    name = object.getString("birthday"); // 01/31/1980 format
//                                    Log.v("LoginActivity", name);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                try {
//                                    providerKey = object.getString("provider_key"); // 01/31/1980 format
//                                    Log.v("LoginActivity", providerKey);
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                                Toast.makeText(getApplicationContext(), "Login Done", Toast.LENGTH_SHORT).show();
//
//                                Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();
//
//                                JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);
//
//                                jaylakAPI.socialLogin("Facebook", name, email, providerKey, profilePicture).enqueue(new Callback<ResponseBody>() {
//                                    @Override
//                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                                    }
//                                });
//                            }
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,gender,birthday");
//                request.setParameters(parameters);
//                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookAccessToken = AccessToken.getCurrentAccessToken();
                Log.d("Success", "Login");
                Log.d("facebook", "Done so redirecting with " + facebookAccessToken.getToken());

                Profile profile = Profile.getCurrentProfile();

                Log.d("facebook name", profile.getName());
                Log.d("facebook name", String.valueOf(profile.getProfilePictureUri(500, 500)));


                profile.getName();
                profile.getProfilePictureUri(500, 500);

                Toast.makeText(getApplicationContext(), "Login Success" + "\n" + profile.getProfilePictureUri(500, 500) + "\n" + profile.getName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        facebookCustomLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email", "user_friends"));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password;

                email = emailET.getText().toString().trim();
                password = passwordET.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {

                    login(email, password);

                } else if (!email.isEmpty() && password.isEmpty()) {
                    passwordET.setError("Required");
                } else {
                    emailET.setError("Required");
                }
            }
        });

        forgotPasswordTVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(String email, String password) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(JaylakAPI.JAYLAK_API_URL).build();

        final JaylakAPI jaylakAPI = retrofit.create(JaylakAPI.class);

        jaylakAPI.userLogin(email, password).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.code() == 200) {
                    try {

                        mChuckData = response.body().string();
                        JSONObject jsonObject = new JSONObject(mChuckData);
                        API_TOKEN = jsonObject.getJSONObject("data").getString("api_token");
                        Toast.makeText(getApplicationContext(), "Login Done" + "\n" + API_TOKEN, Toast.LENGTH_LONG).show();
                        Log.v("Token", API_TOKEN);
                        Log.v("Token", mChuckData);

                        Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        intent.putExtra("API_TOKEN", API_TOKEN);
                        startActivity(intent);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    //  mChuckData = response.errorBody().string();
                    try {
                        mChuckData = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(mChuckData);

                        Toast.makeText(getApplicationContext(), jsonObject.getString("data"), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void initElements() {

        emailET = findViewById(R.id.et_login_email);
        passwordET = findViewById(R.id.et_login_password);
        signInButton = findViewById(R.id.btn_login); // default app sign in button
        googleSignInCustomButton = findViewById(R.id.btn_google_sign_in);
        singUpTVButton = findViewById(R.id.signup_tv_button);
        facebookCustomLoginButton = findViewById(R.id.btn_facebook_login);
        forgotPasswordTVButton = findViewById(R.id.forgot_password);
        facebookLoginButton = (LoginButton) findViewById(R.id.btn_facebook_login_default); // facebook default login button
        googleSingInButton = findViewById(R.id.btn_google_sign_in_default);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        facebookAccessToken = AccessToken.getCurrentAccessToken();
        if (facebookAccessToken != null) {
            // Toast.makeText(getApplicationContext(), facebookAccessToken.getToken(), Toast.LENGTH_SHORT).show();
            //    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
            // startActivity(new Intent(this, CategoryActivity.class));
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}