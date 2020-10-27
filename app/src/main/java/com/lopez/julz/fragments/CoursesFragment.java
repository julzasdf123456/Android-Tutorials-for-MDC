package com.lopez.julz.fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lopez.julz.adapters.CoursesAdapter;
import com.lopez.julz.classes.Course;
import com.lopez.julz.db.DBHelper;
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

    public DBHelper dbHelper;
    public FloatingActionButton newCourseBtn;

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

        dbHelper = new DBHelper(getActivity());
        newCourseBtn = (FloatingActionButton) view.findViewById(R.id.createNewCourse);

        newCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCourse();
            }
        });

        populateData();

        return view;
    }

    public void populateData() {
        courseList.clear();

        Cursor cursor = dbHelper.getCourses();
        while (cursor.moveToNext()) {
            courseList.add(new Course(cursor.getString(cursor.getColumnIndex("CourseName")), cursor.getString(cursor.getColumnIndex("CourseDescription")), R.drawable.ic_img_2));
        }

        coursesAdapter.notifyDataSetChanged();
    }

    public void createNewCourse() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Course");

        // Set up the input
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText coursename = new EditText(getActivity());
        coursename.setInputType(InputType.TYPE_CLASS_TEXT);
        coursename.setHint("Type Course Name");

        final EditText courseDescription = new EditText(getActivity());
        courseDescription.setInputType(InputType.TYPE_CLASS_TEXT);
        courseDescription.setHint("Type Course Description");

        layout.addView(coursename);
        layout.addView(courseDescription);

        builder.setView(layout);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dbHelper.insertNewCourse(coursename.getText().toString(), courseDescription.getText().toString())) {
                    dialog.dismiss();
                    populateData();
                    Snackbar.make(newCourseBtn, "New Course Inserted!", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(newCourseBtn, "Saving new course failed!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}