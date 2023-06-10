package com.example.teamfind.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamfind.R;
import com.example.teamfind.data.Category;
import com.example.teamfind.data.Project;
import com.example.teamfind.databinding.FragmentProjectBinding;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    List<Project> list;
    private final LayoutInflater inflater;
    interface OnProjectClickListener{
        void onProjectClick(ViewHolder holder);
    }
    private final OnProjectClickListener clickListener;

    public ProjectAdapter(Context context, List<Project> projects, OnProjectClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        this.list = projects;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentProjectBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onProjectClick(holder);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        private final FragmentProjectBinding itemBinding;
        public String name;
        public String description;
        public String author;
        public String date;
        public String id;
        public String author_id;

        public Category cat1;
        public Category cat2;
        public Category cat3;
        public Category cat4;
        public Category cat5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = FragmentProjectBinding.bind(itemView);


        }

        public void bind(Project project){
            name = project.name;
            description = project.description;
            if(project.author.name != null)
                author = project.author.name;
            else
                author = "Неизвестный";
            date = project.date;
            id = project.DBid;
            author_id = project.author_id;


            cat1 = project.categories[0];
            cat2 = project.categories[1];
            cat3 = project.categories[2];
            cat4 = project.categories[3];
            cat5 = project.categories[4];

            itemBinding.name.setText(project.name);
            if(project.author != null)
                itemBinding.author.setText(project.author.name);
            else
                itemBinding.author.setText("Неизвестный");
            itemBinding.date.setText(project.date);

            itemBinding.cat1.setText(project.categories[0].name);
            if(project.categories[0].drawable_id == R.drawable.blue || project.categories[0].drawable_id == R.drawable.violet
                    || project.categories[0].drawable_id == R.drawable.redd)
                itemBinding.cat1.setTextColor(Color.WHITE);
            itemBinding.cat1.setBackgroundResource(project.categories[0].drawable_id);
            itemBinding.cat2.setText(project.categories[1].name);
            if(project.categories[1].drawable_id == R.drawable.blue || project.categories[1].drawable_id == R.drawable.violet
                    || project.categories[1].drawable_id == R.drawable.redd)
                itemBinding.cat2.setTextColor(Color.WHITE);
            itemBinding.cat2.setBackgroundResource(project.categories[1].drawable_id);
            itemBinding.cat3.setText(project.categories[2].name);
            if(project.categories[2].drawable_id == R.drawable.blue || project.categories[2].drawable_id == R.drawable.violet
                    || project.categories[2].drawable_id == R.drawable.redd)
                itemBinding.cat3.setTextColor(Color.WHITE);
            itemBinding.cat3.setBackgroundResource(project.categories[2].drawable_id);
            itemBinding.cat4.setText(project.categories[3].name);
            if(project.categories[3].drawable_id == R.drawable.blue || project.categories[3].drawable_id == R.drawable.violet
                    || project.categories[3].drawable_id == R.drawable.redd)
                itemBinding.cat4.setTextColor(Color.WHITE);
            itemBinding.cat4.setBackgroundResource(project.categories[3].drawable_id);
            itemBinding.cat5.setText(project.categories[4].name);
            if(project.categories[4].drawable_id == R.drawable.blue || project.categories[4].drawable_id == R.drawable.violet
                    || project.categories[4].drawable_id == R.drawable.redd)
                itemBinding.cat5.setTextColor(Color.WHITE);
            itemBinding.cat5.setBackgroundResource(project.categories[4].drawable_id);
        }
    }
}
