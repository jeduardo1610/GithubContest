package com.example.m14x.githubcontest.View;

import android.app.ProgressDialog;
import android.content.Context;

public class Progress {

    private ProgressDialog pd;

    public void init(Context context){
        pd = new ProgressDialog(context);
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
    }

    public void show(){
        if(pd != null){
            pd.show();
        }
    }

    public void hide (){
        if(pd != null){
            pd.hide();
        }
    }

}
