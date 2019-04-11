package com.the.bamstroyputs.project;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.ItemProjectBinding;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.GeneralViewHolder> {
    private List<Project> list;
    private OnItemClickListener listener;

    public ProjectAdapter(OnItemClickListener listener){
        this.listener = listener;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemProjectBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_project, viewGroup, false);

        return new GeneralViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder generalViewHolder, final int i) {
        Project item = list.get(i);
        generalViewHolder.bind(item);
        generalViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(list.get(i).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void addItem(Project item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void setList(List<Project> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class GeneralViewHolder extends RecyclerView.ViewHolder {
        final ItemProjectBinding binding;

        public GeneralViewHolder(@NonNull ItemProjectBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Project item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
