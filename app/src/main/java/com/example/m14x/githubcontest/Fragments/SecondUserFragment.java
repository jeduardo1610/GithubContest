package com.example.m14x.githubcontest.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m14x.githubcontest.Constants;
import com.example.m14x.githubcontest.Controller.CustomAdapter;
import com.example.m14x.githubcontest.Model.RepoDetail;
import com.example.m14x.githubcontest.R;

import java.util.ArrayList;

public class SecondUserFragment extends Fragment {
    private ArrayList<RepoDetail> content;
    private RecyclerView recyclerView;

    public static SecondUserFragment newInstance(ArrayList<RepoDetail> content) {
        SecondUserFragment fragment = new SecondUserFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.FRAGMENT_ARG_KEY, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getParcelableArrayList(Constants.FRAGMENT_ARG_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_user, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        CustomAdapter adapter = new CustomAdapter(view.getContext(),content);
        recyclerView.setAdapter(adapter);


        return view;
    }
}