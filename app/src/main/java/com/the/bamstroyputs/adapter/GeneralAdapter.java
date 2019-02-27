package com.the.bamstroyputs.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.ItemGeneralBinding;
import com.the.bamstroyputs.model.GeneralItem;

import java.util.ArrayList;

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GeneralViewHolder> {
    private ArrayList<GeneralItem> list;

    @NonNull
    @Override
    public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemGeneralBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_general, viewGroup, false);

        return new GeneralViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralViewHolder generalViewHolder, int i) {
        GeneralItem item = list.get(i);
        generalViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setList(ArrayList<GeneralItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class GeneralViewHolder extends RecyclerView.ViewHolder{
        final ItemGeneralBinding binding;

        public GeneralViewHolder(@NonNull ItemGeneralBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GeneralItem item){
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
//        Glide.with(context)
//                .load(R.drawable.building)
//                .into(holder.icon_project);
//