package com.example.m14x.githubcontest.Controller;

import java.util.List;

public interface RetrofitRequestInteractor {

    void onRequestSuccess(List result, String userId);
    void onRequestFailure(String throwableError);

}
