package com.example.m14x.githubcontest.Controller;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/users/{user}/repos?sort=created")
    Call<JsonArray> getContent(@Path(value = "user",encoded = true)String userId, @Query(value="page")String page);
}


