package com.lopez.julz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.lopez.julz.classes.Course;
import com.lopez.julz.myfirstdumbapplication.R;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {

    public List<Course> courseList;
    public Context context;

    public CoursesAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.recyclerview_courses, parent, false);

        // Return a new holder instance
        CoursesAdapter.ViewHolder viewHolder = new CoursesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        final Course course = courseList.get(position);

        holder.image.setImageResource(course.getImage());
        holder.title.setText(course.getTitle());
        holder.description.setText(course.getDescription());

        holder.coursesParentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, course.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title, description;
        public MaterialCardView coursesParentCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.coursesImage);
            title = (TextView) itemView.findViewById(R.id.courseName);
            description = (TextView) itemView.findViewById(R.id.courseDescription);
            coursesParentCard = (MaterialCardView) itemView.findViewById(R.id.coursesParentCard);
        }
    }
}
