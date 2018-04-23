package com.example.android.jaylak.Interface;

import com.example.android.jaylak.Models.Category;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Tech-4 on 2/24/2018.
 */

public interface JaylakAPI {

    String JAYLAK_API_URL = "https://jaylakstaging.betahubs.info/en/api/v1/";

    @POST("register")
    @FormUrlEncoded
    Call<ResponseBody> userRegister(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("phone") String mobile,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String passwordConfirmation);

    @POST("login")
    @FormUrlEncoded
    Call<ResponseBody> userLogin(@Field("email") String email,
                                 @Field("password") String password);

    // login with facebook or google
    @POST("social/login")
    @FormUrlEncoded
    Call<ResponseBody> socialLogin(@Field("login_provider") String loginProvider,
                                   @Field("name") String name,
                                   @Field("email") String email,
                                   @Field("provider_key") String providerKey,
                                   @Field("image") String image);

    @POST("password/email")
    @FormUrlEncoded
    Call<ResponseBody> getPassword(@Field("email") String email);

    @GET("brands/latest")
    Call<ResponseBody> brands();

    @GET("categories")
    Call<Category> getCategories();

    @GET("profile")
    Call<ResponseBody> getProfile(@Header("Authorization") String API_TOKEN);

    @GET("brands/{id}")
    Call<ResponseBody> getBrands(@Header("Authorization") String auth, @Path("id") int id);

    @GET("user/profile")
    Call<ResponseBody> getUserProfile(@Header("Authorization") String API_TOKEN);

    @GET("items/{id}")
    Call<ResponseBody> getBrandDetails(@Header("Authorization") String API_TOKEN, @Path("id") int id);

    @GET("rating/{id}")
    Call<ResponseBody> getBrandRates(@Header("Authorization") String API_TOKEN, @Path("id") int id);

    @GET("rateables/{id}")
    Call<ResponseBody> getBrandRateAble(@Header("Authorization") String API_TOKEN, @Path("id") int id);


}