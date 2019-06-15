package com.the.bamstroyputs.room;

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
    public void onBindViewHolder(@NonNull final RoomViewHolder roomViewHolder, int i) {
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

        roomViewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), roomViewHolder.menu);
                popup.inflate(R.menu.menu_room);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.rename:
                                Toast.makeText(v.getContext(), "Rename", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.addNick:
                                Toast.makeText(v.getContext() , "Add nick" , Toast.LENGTH_SHORT).show();
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
        ImageView menu;

        public RoomViewHolder(@NonNull ItemRoomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            menu = itemView.findViewById(R.id.menu_room);
        }

        public void bind(Room room){
            binding.setRoom(room);
            binding.executePendingBindings();
        }
    }
}
