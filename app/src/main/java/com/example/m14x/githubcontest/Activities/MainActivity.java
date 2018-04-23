package com.example.m14x.githubcontest.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m14x.githubcontest.BitmapUtil.BuildImageBitmap;
import com.example.m14x.githubcontest.BitmapUtil.BuildImageListener;
import com.example.m14x.githubcontest.Constants;
import com.example.m14x.githubcontest.Controller.RetrofitRequest;
import com.example.m14x.githubcontest.Controller.RetrofitRequestInteractor;
import com.example.m14x.githubcontest.Model.RepoDetail;
import com.example.m14x.githubcontest.R;
import com.example.m14x.githubcontest.View.Progress;
import com.example.m14x.githubcontest.View.SimpleDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RetrofitRequestInteractor, View.OnClickListener{

    private ArrayList<RepoDetail> resultDetailUser1 = new ArrayList<>();
    private ArrayList<RepoDetail> resultDetailUser2 = new ArrayList<>();
    private Progress progress;
    private Button goButton;
    private ImageView avatarUser1;
    private ImageView avatarUser2;
    private ImageView imageViewTrophy1;
    private ImageView imageViewTrophy2;
    private TextView textViewUser1;
    private TextView textViewUser2;
    private TextInputLayout textInputUser1;
    private TextInputLayout textInputUser2;
    private String userId1;
    private String userId2;
    private int requestCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new Progress();
        progress.init(this);

        goButton = findViewById(R.id.button_go);
        imageViewTrophy1 = findViewById(R.id.imageView_trophy1);
        imageViewTrophy2 = findViewById(R.id.imageView_thropy2);
        avatarUser1 = findViewById(R.id.imageView_avatar_user_1);
        avatarUser2 = findViewById(R.id.imageView_avatar_user_2);
        textViewUser1 = findViewById(R.id.textView_username1);
        textViewUser2 = findViewById(R.id.textView_username2);
        textInputUser1 = findViewById(R.id.textInput_username1);
        textInputUser2 = findViewById(R.id.textInput_username2);
        goButton.setOnClickListener(this);
        avatarUser1.setOnClickListener(this);
        avatarUser2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_repo:

                if(requestCounter > 0){
                    Intent repo = new Intent(MainActivity.this, RepositoriesActivity.class);
                    repo.putExtra(Constants.REPO_LIST_1_KEY_EXTRA, resultDetailUser1);
                    repo.putExtra(Constants.REPO_LIST_2_KEY_EXTRA, resultDetailUser2);
                    startActivity(repo);
                }else{
                    SimpleDialog simpleDialog = new SimpleDialog();
                    simpleDialog.init(MainActivity.this, getResources().getString(R.string.no_content), getResources().getString(R.string.not_ready));
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestSuccess(List result, String userId) {
        if(userId.equals(userId1)){
            requestCounter++;
            resultDetailUser1.addAll(result);
            String url = resultDetailUser1.size()>0 ? resultDetailUser1.get(0).getAvatar() : "";
            downloadAvatarImage(avatarUser1,url);
        }else{
            requestCounter++;
            resultDetailUser2.addAll(result);
            String url = resultDetailUser2.size()>0 ? resultDetailUser2.get(0).getAvatar() : "";
            downloadAvatarImage(avatarUser2,url);
        }

        if(requestCounter == 2){
            progress.hide();
            lookForTheWinner();
        }
    }

    @Override
    public void onRequestFailure(String throwableError) {
    progress.hide();
    SimpleDialog simpleDialog = new SimpleDialog();
    simpleDialog.init(MainActivity.this, getResources().getString(R.string.something_wrong), getResources().getString(R.string.try_again));
    }

    public void lookForTheWinner(){
        int starsUser1 = getStarsCount(resultDetailUser1);
        int starsUser2 = getStarsCount(resultDetailUser2);

        if(starsUser1 > starsUser2){
            //user1 is the winner
            //will show trophy under user1 avatar
            imageViewTrophy1.setVisibility(View.VISIBLE);
            imageViewTrophy2.setVisibility(View.INVISIBLE);
        }else if(starsUser1 < starsUser2){
            //user 2 is the winner
            //will show trophy under user2 avatar
            imageViewTrophy1.setVisibility(View.INVISIBLE);
            imageViewTrophy2.setVisibility(View.VISIBLE);
        } else{
            //it was a draw, both has the same number of stars
            //will show trophy in the middle
            imageViewTrophy1.setVisibility(View.GONE);
            imageViewTrophy2.setVisibility(View.VISIBLE);
        }
    }

    public int getStarsCount(ArrayList<RepoDetail> data){
        int count = 0;
        for(RepoDetail item : data){
            count += item.getStargazers_count();
        }
        return count;
    }

    public void downloadAvatarImage(final ImageView view, String url){
             BuildImageBitmap buildImageBitmap = new BuildImageBitmap(new BuildImageListener() {
                    @Override
                    public void onBuildComplete(Bitmap bitmap) {
                        view.setVisibility(View.VISIBLE);
                        view.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBuildError() {
                        view.setVisibility(View.VISIBLE);
                        view.setImageDrawable(getResources().getDrawable(R.drawable.octocat));
                    }
                });
                try {
                    buildImageBitmap.execute(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    public void goToProfile(String url){
        if(url.contains("http")){
            //Setting chrome custom tab to display profile
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_go:
                requestCounter = 0;
                imageViewTrophy1.setVisibility(View.GONE);
                imageViewTrophy2.setVisibility(View.GONE);
                userId1 = "";
                userId2 = "";
                resultDetailUser1.clear();
                resultDetailUser2.clear();
                avatarUser1.setImageDrawable(null);
                avatarUser2.setImageDrawable(null);
                userId1 = textViewUser1.getText().toString();
                userId2 = textViewUser2.getText().toString();
                if(userId1.isEmpty()){
                    textInputUser1.setError(getResources().getString(R.string.empty_field));
                }else if (userId2.isEmpty()){
                    textInputUser2.setError(getResources().getString(R.string.empty_field));
                }else{
                    progress.show();
                    textInputUser1.setError("");
                    textInputUser2.setError("");
                    RetrofitRequest.getInstance().makeRequest(userId1, 1, MainActivity.this);
                    RetrofitRequest.getInstance().makeRequest(userId2, 1,MainActivity.this);

                }
            break;

            case R.id.imageView_avatar_user_1:
                if(resultDetailUser1.size() > 0){
                    goToProfile(resultDetailUser1.get(0).getProfile());
                }
            break;
            case R.id.imageView_avatar_user_2:
                if(resultDetailUser2.size() > 0){
                    goToProfile(resultDetailUser2.get(0).getProfile());
                }
            break;

        }
    }
}
