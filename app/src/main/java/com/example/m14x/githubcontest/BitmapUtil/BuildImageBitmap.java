package com.example.m14x.githubcontest.BitmapUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

public class BuildImageBitmap extends AsyncTask<String, Bitmap, Bitmap> {

    Bitmap bitmap;
    BuildImageListener listener;
    public BuildImageBitmap (BuildImageListener listener){
        this.listener = listener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Bitmap doInBackground(String... URL) {

        String imageURL = URL[0];

        Bitmap bitmap = null;
        try {
            // Download Image from URL
            InputStream input = new java.net.URL(imageURL).openStream();
            // Decode Bitmap
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {

        if(result != null){
            listener.onBuildComplete(result);
        }else{
            listener.onBuildError();
        }
    }
}