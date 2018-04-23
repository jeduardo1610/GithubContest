package com.example.m14x.githubcontest.Controller;

import com.example.m14x.githubcontest.Constants;
import com.example.m14x.githubcontest.Model.RepoDetail;
import com.example.m14x.githubcontest.Model.RetrofitInstance;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitRequest {

    private ArrayList<RepoDetail> result = new ArrayList<>();
    private Call<JsonArray> response;


    public static RetrofitRequest getInstance(){
        return new RetrofitRequest();
    }

    public void makeRequest(final String userId, final int page, final RetrofitRequestInteractor listener){
        response = RetrofitInstance.apiService.getContent(userId, String.valueOf(page));
        response.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                try{
                    if (response.code() == 200){
                        if (response.body() != null){
                            JsonArray arrayResult = response.body();
                            if (arrayResult.size() > 0){
                                for (JsonElement itemResult : arrayResult){
                                    RepoDetail detail = new RepoDetail();
                                    JsonObject fullItem = itemResult.getAsJsonObject();
                                    int stars = fullItem.get(Constants.STARS).getAsInt();
                                    String url_repo = fullItem.get(Constants.URL_REPO).getAsString();
                                    String name = fullItem.get(Constants.NAME).getAsString();
                                    String full_name = fullItem.get(Constants.FULL_NAME).getAsString();

                                    JsonObject owner = fullItem.get(Constants.OWNER).getAsJsonObject();
                                    String avatar = owner.get(Constants.AVATAR).getAsString();
                                    String profile = owner.get(Constants.PROFILE).getAsString();
                                    String login = owner.get(Constants.LOGIN).getAsString();
                                    detail.setStargazers_count(stars);
                                    detail.setHtml_url(url_repo);
                                    detail.setName(name);
                                    detail.setFull_name(full_name);
                                    detail.setAvatar(avatar);
                                    detail.setProfile(profile);
                                    detail.setLogin(login);
                                    result.add(detail);
                                }


                                if(arrayResult.size() >= Constants.MAX_RESULT_SIZE){
                                    //if content is more than 30 (which is the defined numbre of rows by page)
                                    //let's request the next page
                                    int next = page + 1;
                                    makeRequest(userId, next, listener);
                                }else{
                                    listener.onRequestSuccess(result, userId);
                                }

                            }else{
                                listener.onRequestFailure("Unknown Error");
                            }

                        }
                    }else{
                        //This is implemented to cover the case when the user id does not exists, request will return a code different to 200
                        listener.onRequestFailure("Unknown Error");
                    }

                }catch (NullPointerException e){
                    listener.onRequestFailure(e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                listener.onRequestFailure(t.getLocalizedMessage());
            }
        });
    }

}
