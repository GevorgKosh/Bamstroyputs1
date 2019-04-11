package com.the.bamstroyputs.chair;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.the.bamstroyputs.R;

import java.util.List;

public class ChairListAdapter extends RecyclerView.Adapter<ChairListAdapter.MyViewHolder> {

    private List<String> chairNamesList;
    private List<String> countFirst;
    private List<String> countSecond;
    private List<String> countThird;
    private List<Integer> iconsList;


    public ChairListAdapter(List<String> chairNamesList, List<String> countFirst, List<String> countSecond, List<String> countThird, List<Integer> iconsList) {
        this.chairNamesList = chairNamesList;
        this.countFirst = countFirst;
        this.countSecond = countSecond;
        this.countThird = countThird;
        this.iconsList = iconsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_chair_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        String chairName = chairNamesList.get(i);
        String numFirst = countFirst.get(i);
        String numSecond = countSecond.get(i);
        String numThird = countThird.get(i);
        int icon = iconsList.get(i);
        myViewHolder.chairName.setText(chairName);
        myViewHolder.countFirst.setText(numFirst);
        myViewHolder.countSecond.setText(numSecond);
        myViewHolder.countThird.setText(numThird);
        myViewHolder.chairIcon = icon;

        myViewHolder.menuChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), myViewHolder.menuChair);
                popup.inflate(R.menu.menu_chair_list);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.changeCount:
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
        return chairNamesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView chairName,
                countFirst,
                countSecond,
                countThird;
        public int chairIcon;
        public ImageView menuChair;

        public MyViewHolder(View view) {
            super(view);
            chairName = view.findViewById(R.id.chairName);
            countFirst = view.findViewById(R.id.numHave);
            countSecond = view.findViewById(R.id.numCount);
            countThird = view.findViewById(R.id.numOrdered);
            menuChair = view.findViewById(R.id.menu_chair);
        }
    }


}
