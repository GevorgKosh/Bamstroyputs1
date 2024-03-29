package com.the.bamstroyputs.floor;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.ItemFloorBinding;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Floor;

import java.util.ArrayList;
import java.util.List;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.FloorViewHolder>{
    private List<Floor> list;
    private OnItemClickListener listener;

    public FloorAdapter(OnItemClickListener listener) {
        list = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public FloorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemFloorBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_floor, viewGroup, false);

        return new FloorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final FloorViewHolder floorViewHolder, final int i) {
        final Floor floor = list.get(i);
        floorViewHolder.bind(floor);
        floorViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(list.get(i).getId());
                }
            }
        });
        floorViewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), floorViewHolder.menu);
                popup.inflate(R.menu.menu_floor);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.change:
                                Toast.makeText(v.getContext(), "All is ok", Toast.LENGTH_SHORT).show();
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

    public void setList(List<Floor> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(Floor floor){
        list.add(floor);
        notifyItemInserted(list.size() - 1);
    }

    class FloorViewHolder extends RecyclerView.ViewHolder{
        ItemFloorBinding binding;
        ImageView menu;
        public FloorViewHolder(@NonNull ItemFloorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            menu = itemView.findViewById(R.id.menu_floor);

        }

        public void bind(Floor floor){
            binding.setFloor(floor);
            binding.executePendingBindings();
        }
    }
}
