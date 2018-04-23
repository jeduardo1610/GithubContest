package com.example.m14x.githubcontest;

public class Constants {

    public static String STARS = "stargazers_count";
    public static String URL_REPO = "html_url";
    public static String NAME = "name";
    public static String FULL_NAME = "full_name";
    public static String OWNER = "owner";
    public static String AVATAR = "avatar_url";
    public static String PROFILE = "html_url";
    public static String LOGIN = "login";

    public static String REPO_LIST_1_KEY_EXTRA = "User1RepoList";
    public static String REPO_LIST_2_KEY_EXTRA = "User2RepoList";
    public static String FRAGMENT_ARG_KEY = "content";

    public static final String BASE_URL = "https://api.github.com";

    public static int MAX_RESULT_SIZE = 30; //Github API to retrieve repositories list retrieve a maximum of 100 items per page, by default is 30
}
