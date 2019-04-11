package com.the.bamstroyputs.buildings;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.ItemBuildingBinding;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Building;

import java.util.ArrayList;
import java.util.List;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder> {
    private List<Building> list;
    private OnItemClickListener listener;

    public BuildingAdapter(OnItemClickListener listener) {
        this.listener = listener;
        list = new ArrayList();
    }

    @NonNull
    @Override
    public BuildingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemBuildingBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_building, viewGroup, false);

        return new BuildingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildingViewHolder projectViewHolder, final int i) {
        Building building = list.get(i);
        projectViewHolder.bind(building);
        projectViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(list.get(i).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setList(List<Building> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(Building building) {
        list.add(building);
        notifyItemInserted(list.size() - 1);
    }

    class BuildingViewHolder extends RecyclerView.ViewHolder {
        ItemBuildingBinding binding;

        public BuildingViewHolder(@NonNull ItemBuildingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Building building) {
            binding.setBuilding(building);
            binding.executePendingBindings();
        }
    }
}
