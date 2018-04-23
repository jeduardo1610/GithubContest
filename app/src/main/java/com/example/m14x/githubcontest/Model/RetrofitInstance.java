package com.example.m14x.githubcontest.Model;

import com.example.m14x.githubcontest.Constants;
import com.example.m14x.githubcontest.Controller.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static ApiService apiService = retrofit.create(ApiService.class);
}