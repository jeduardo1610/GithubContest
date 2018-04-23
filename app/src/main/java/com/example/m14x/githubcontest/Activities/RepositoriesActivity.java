package com.example.m14x.githubcontest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.m14x.githubcontest.Constants;
import com.example.m14x.githubcontest.Controller.ViewPagerAdapter;
import com.example.m14x.githubcontest.Model.RepoDetail;
import com.example.m14x.githubcontest.R;

import java.util.ArrayList;

public class RepositoriesActivity extends AppCompatActivity {

    private ArrayList<RepoDetail> repoListUser1;
    private ArrayList<RepoDetail> repoListUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        getExtras();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViewPager();
    }

    public void setUpViewPager(){
        ViewPager vpPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager(), repoListUser1, repoListUser2);
        vpPager.setAdapter(adapterViewPager);
    }

    public void getExtras(){
        Intent incoming = getIntent();
        if(incoming != null){
            repoListUser1 = incoming.getParcelableArrayListExtra(Constants.REPO_LIST_1_KEY_EXTRA);
            repoListUser2 = incoming.getParcelableArrayListExtra(Constants.REPO_LIST_2_KEY_EXTRA);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
