package com.example.m14x.githubcontest.Controller;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.m14x.githubcontest.Model.RepoDetail;
import com.example.m14x.githubcontest.R;

import java.util.List;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView fullName;
    private TextView stars;
    private CardView cardView;

    public CustomViewHolder(View itemView, List<RepoDetail> content) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        fullName = itemView.findViewById(R.id.tv_full_name);
        stars = itemView.findViewById(R.id.tv_stars);
        cardView = itemView.findViewById(R.id.cardView);
    }

    public void setItem(RepoDetail repoDetail) {
        name.setText(repoDetail.getName());
        fullName.setText(repoDetail.getFull_name());
        stars.setText(String.valueOf(repoDetail.getStargazers_count()));
    }

    public CardView getCardView(){
        return cardView;
    }
}