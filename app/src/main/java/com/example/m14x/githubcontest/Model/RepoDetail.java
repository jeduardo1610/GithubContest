package com.example.m14x.githubcontest.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class RepoDetail implements Parcelable{

    private int stargazers_count;
    private String html_url;
    private String name;
    private String full_name;
    private String avatar;
    private String profile;
    private String login;

    public RepoDetail(Parcel in) {
        stargazers_count = in.readInt();
        html_url = in.readString();
        name = in.readString();
        full_name = in.readString();
        avatar = in.readString();
        profile = in.readString();
        login = in.readString();
    }

    public RepoDetail(){

    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(stargazers_count);
        dest.writeString(html_url);
        dest.writeString(name);
        dest.writeString(full_name);
        dest.writeString(avatar);
        dest.writeString(profile);
        dest.writeString(login);
    }

    public static final Creator<RepoDetail> CREATOR = new Creator<RepoDetail>() {
        @Override
        public RepoDetail createFromParcel(Parcel in) {
            return new RepoDetail(in);
        }

        @Override
        public RepoDetail[] newArray(int size) {
            return new RepoDetail[size];
        }
    };

}
