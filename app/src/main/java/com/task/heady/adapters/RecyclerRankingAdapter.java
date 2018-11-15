package com.task.heady.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.task.heady.R;
import com.task.heady.activities.HeadyActivity;
import com.task.heady.models.Ranking;

import java.util.ArrayList;
import java.util.List;

public class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.ItemHolder> {

    public Context mContext;
    public ArrayList<Ranking> rankingArrayList;
    int height = 0;
    int width = 0;
    boolean isSelected = false;
    int selected_position = 0;

    public RecyclerRankingAdapter(Context context, List<Ranking> results) {
        this.mContext = context;
        this.rankingArrayList = (ArrayList<Ranking>) results;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ranking_item, viewGroup, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {

            Ranking ranking = rankingArrayList.get(position);

            itemHolder.rankingItem.setCardBackgroundColor(mContext.getResources().getColor(R.color.background_primary));
            itemHolder.rankingItem.setTag(position);
            itemHolder.txtRankinName.setText(ranking.getRanking());

            if (isSelected) {
                if (selected_position == position) {
                    itemHolder.txtRankinName.setTextColor(mContext.getResources().getColor(R.color.colordanger));
                } else {
                    itemHolder.txtRankinName.setTextColor(mContext.getResources().getColor(R.color.primary_color_two));
                }
            } else {
                itemHolder.txtRankinName.setTextColor(mContext.getResources().getColor(R.color.primary_color_two));
            }

    }


    @Override
    public int getItemCount() {
        return rankingArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private CardView rankingItem;
        private TextView txtRankinName;

        public ItemHolder(View view) {
            super(view);
            rankingItem = (CardView) view.findViewById(R.id.rankingItem);
            rankingItem.setMinimumWidth(width / 2);
            rankingItem.setMinimumHeight(30);
            txtRankinName = (TextView) view.findViewById(R.id.txtRankinName);
            rankingItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getTag() != null) {
                        int position = (int) v.getTag();
                        ((HeadyActivity)mContext).handleRankingRecyclerClick(position);
                    }
                }
            });
        }
    }

    public void updateRankingList (boolean selected, int position) {
        this.isSelected = selected;
        this.selected_position = position;
        notifyDataSetChanged();
    }
}

