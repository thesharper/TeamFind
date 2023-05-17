package com.example.teamfind.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teamfind.R;
import com.example.teamfind.data.Category;
import com.example.teamfind.data.Project;
import com.example.teamfind.databinding.FragmentProjectBinding;

public class ProjectFragment extends Fragment {
    FragmentProjectBinding binding;
    String name;
    String author;
    String date;
    Category[] categories;
    public ProjectFragment(Project project){
        this.name = project.name;
        this.author = project.author.name;
        this.date = project.date;
        this.categories = project.categories;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProjectBinding.inflate(inflater, container, false);
        binding.name.setText(name);
        binding.author.setText(R.string.author + author);
        binding.date.setText(R.string.date + date);

        switch (categories.length) {
            case 1:
                binding.cat1.setText(categories[0].name);
                binding.cat1.setBackgroundResource(categories[0].drawable_id);
                break;
            case 2:
                binding.cat1.setText(categories[0].name);
                binding.cat1.setBackgroundResource(categories[0].drawable_id);
                binding.cat2.setText(categories[1].name);
                binding.cat2.setBackgroundResource(categories[1].drawable_id);
                break;
            case 3:
                binding.cat1.setText(categories[0].name);
                binding.cat1.setBackgroundResource(categories[0].drawable_id);
                binding.cat2.setText(categories[1].name);
                binding.cat2.setBackgroundResource(categories[1].drawable_id);
                binding.cat3.setText(categories[2].name);
                binding.cat3.setBackgroundResource(categories[2].drawable_id);
                break;
            case 4:
                binding.cat1.setText(categories[0].name);
                binding.cat1.setBackgroundResource(categories[0].drawable_id);
                binding.cat2.setText(categories[1].name);
                binding.cat2.setBackgroundResource(categories[1].drawable_id);
                binding.cat3.setText(categories[2].name);
                binding.cat3.setBackgroundResource(categories[2].drawable_id);
                binding.cat4.setText(categories[3].name);
                binding.cat4.setBackgroundResource(categories[3].drawable_id);
                break;
            case 5:
                binding.cat1.setText(categories[0].name);
                binding.cat1.setBackgroundResource(categories[0].drawable_id);
                binding.cat2.setText(categories[1].name);
                binding.cat2.setBackgroundResource(categories[1].drawable_id);
                binding.cat3.setText(categories[2].name);
                binding.cat3.setBackgroundResource(categories[2].drawable_id);
                binding.cat4.setText(categories[3].name);
                binding.cat4.setBackgroundResource(categories[3].drawable_id);
                binding.cat5.setText(categories[4].name);
                binding.cat5.setBackgroundResource(categories[4].drawable_id);
                break;
        }

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
