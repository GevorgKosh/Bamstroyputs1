package com.the.bamstroyputs.project;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.ItemProjectBinding;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.interfaces.OnMenuClickListener;
import com.the.bamstroyputs.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.GeneralViewHolder> {
    private List<Project> list;
    private OnItemClickListener listener;
    private OnMenuClickListener menuClickListener;

    public ProjectAdapter(OnItemClickListener listener, OnMenuClickListener menuClickListener) {
        this.listener = listener;
        this.menuClickListener = menuClickListener;
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
    public void onBindViewHolder(@NonNull final GeneralViewHolder generalViewHolder, final int i) {
        Project item = list.get(i);
        generalViewHolder.bind(item);
        generalViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(list.get(i).getId());
            }
        });

        generalViewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), generalViewHolder.menu);
                popup.inflate(R.menu.menu_project);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.change:
                                menuClickListener.onMenuItemClick(list.get(i).getId());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
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
        ImageView menu;

        public GeneralViewHolder(@NonNull ItemProjectBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            menu = itemView.findViewById(R.id.menu_project);
        }

        public void bind(Project item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
