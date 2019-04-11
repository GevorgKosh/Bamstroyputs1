package com.the.bamstroyputs.room;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.ItemRoomBinding;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{
    private List<Room> list;
    private OnItemClickListener listener;

    public RoomAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ItemRoomBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_room, viewGroup, false);

        return new RoomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int i) {
        final Room room = list.get(i);
        roomViewHolder.bind(room);
        roomViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(room.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setList(List<Room> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addRoom(Room room){
        list.add(room);
        notifyItemInserted(list.size() - 1);
    }

    class RoomViewHolder extends RecyclerView.ViewHolder{
        ItemRoomBinding binding;

        public RoomViewHolder(@NonNull ItemRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Room room){
            binding.setRoom(room);
            binding.executePendingBindings();
        }
    }
}
