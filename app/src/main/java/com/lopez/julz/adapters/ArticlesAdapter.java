package com.lopez.julz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lopez.julz.classes.Articles;
import com.lopez.julz.myfirstdumbapplication.R;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    public List<Articles> articlesList;
    public Context context;

    public ArticlesAdapter(List<Articles> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView articleImage;
        public TextView articleTitle, articleDescription, articleDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = (ImageView) itemView.findViewById(R.id.articleImage);
            articleTitle = (TextView) itemView.findViewById(R.id.articleTitle);
            articleDescription = (TextView) itemView.findViewById(R.id.articleContent);
            articleDate = (TextView) itemView.findViewById(R.id.articleDate);
        }
    }

    @NonNull
    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.recyclerview_home_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesAdapter.ViewHolder holder, int position) {
        Articles article = articlesList.get(position);

        holder.articleImage.setImageResource(article.getImage());
        holder.articleTitle.setText(article.getTitle());
        holder.articleDate.setText(article.getDate());
        holder.articleDescription.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}
