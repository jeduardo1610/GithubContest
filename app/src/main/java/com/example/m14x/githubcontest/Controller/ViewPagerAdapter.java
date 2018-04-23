package com.example.m14x.githubcontest.Controller;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.m14x.githubcontest.Fragments.FirstUserFragment;
import com.example.m14x.githubcontest.Fragments.SecondUserFragment;
import com.example.m14x.githubcontest.Model.RepoDetail;

import java.util.ArrayList;
import java.util.List;

public  class ViewPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    private String tabNames[] = new String[2];
    private ArrayList<RepoDetail> repoList1;
    private ArrayList<RepoDetail> repoList2;


    public ViewPagerAdapter(FragmentManager fragmentManager, ArrayList<RepoDetail> repoList1, ArrayList<RepoDetail> repoList2) {
        super(fragmentManager);
        this.repoList1 = repoList1;
        this.repoList2 = repoList2;
        if(repoList1.size() > 0 && repoList2.size() > 0){
            tabNames[0] = repoList1.get(0).getLogin();
            tabNames[1] = repoList2.get(0).getLogin();
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FirstUserFragment.newInstance(repoList1);
            case 1:
                return SecondUserFragment.newInstance(repoList2);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

}