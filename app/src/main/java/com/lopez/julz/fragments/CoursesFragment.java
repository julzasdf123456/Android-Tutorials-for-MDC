package com.lopez.julz.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lopez.julz.adapters.CoursesAdapter;
import com.lopez.julz.classes.Course;
import com.lopez.julz.myfirstdumbapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public RecyclerView recyclerView;
    public List<Course> courseList;
    public CoursesAdapter coursesAdapter;
    public RecyclerView.LayoutManager layoutManager;

    public CoursesFragment() {
        // Required empty public constructor
    }

    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
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
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.coursesRecyclerview);
        courseList = new ArrayList<>();
        coursesAdapter = new CoursesAdapter(courseList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(coursesAdapter);

        populateData();

        return view;
    }

    public void populateData() {
        Course course = new Course("English 101", "Course for English Noobs", R.drawable.ic_nav_header);
        courseList.add(course);

        course = new Course("ITE 201", "Course for IT Not So Noobs", R.drawable.ic_img_2);
        courseList.add(course);

        coursesAdapter.notifyDataSetChanged();
    }
}