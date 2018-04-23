package com.example.m14x.githubcontest.BitmapUtil;

import android.graphics.Bitmap;

public interface BuildImageListener {

    void onBuildComplete(Bitmap bitmap);
    void onBuildError();

}
