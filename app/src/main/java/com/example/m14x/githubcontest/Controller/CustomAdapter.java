package com.example.m14x.githubcontest.Controller;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m14x.githubcontest.Model.RepoDetail;
import com.example.m14x.githubcontest.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<RepoDetail> content;

    public CustomAdapter(Context context, List<RepoDetail> content) {
        this.context = context;
        this.content = content;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater;
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_repo,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view,this.content);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        final RepoDetail repoDetail = content.get(position);
        holder.setItem(repoDetail);

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRepo(repoDetail.getHtml_url());
            }
        });

    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public void goToRepo(String url){
        if(url.contains("http")){
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
        }
    }

}