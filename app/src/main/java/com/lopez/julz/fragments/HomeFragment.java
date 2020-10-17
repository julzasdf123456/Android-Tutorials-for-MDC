package com.lopez.julz.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lopez.julz.adapters.ArticlesAdapter;
import com.lopez.julz.classes.Articles;
import com.lopez.julz.myfirstdumbapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerView homeRecyclerView;
    public List<Articles> articlesList;
    public ArticlesAdapter articlesAdapter;
    public RecyclerView.LayoutManager layoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        articlesList = new ArrayList<>();
        articlesAdapter = new ArticlesAdapter(articlesList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        homeRecyclerView.setLayoutManager(layoutManager);
        homeRecyclerView.setAdapter(articlesAdapter);

        loadData();

        return view;
    }

    public void loadData() {
        Articles article = new Articles("New Event", "October 3, 2020", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English", R.drawable.ic_nav_header);
        articlesList.add(article);

        article = new Articles("Another Article", "October 17, 2020", "This is a sample content of the new article that I added.", R.drawable.ic_nav_header);
        articlesList.add(article);

        articlesAdapter.notifyDataSetChanged();
    }
}