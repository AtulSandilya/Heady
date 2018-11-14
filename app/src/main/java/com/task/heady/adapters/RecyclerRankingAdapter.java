package com.task.heady.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.task.heady.R;
import com.task.heady.models.Ranking;

import java.util.ArrayList;
import java.util.List;

public class RecyclerRankingAdapter extends RecyclerView.Adapter<RecyclerRankingAdapter.ItemHolder> {

    public Context mContext;
    public ArrayList<Ranking> rankingArrayList;
    int height = 0;
    int width = 0;

    public RecyclerRankingAdapter(Context context, List<Ranking> results) {
        this.mContext = context;
        this.rankingArrayList = (ArrayList<Ranking>) results;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
//        itemHolder.txtCategory.setText(category.getName());
//        if (position == selected_position) {
//            itemHolder.txtCategory.setTextColor(mContext.getResources().getColor(R.color.colordanger));
//        } else {
//            itemHolder.txtCategory.setTextColor(mContext.getResources().getColor(R.color.primary_color_two));
//        }
//        itemHolder.llCategoryMain.setTag(position);
//        showFooter(itemHolder, position);
    }

//    private void showFooter(RecyclerCategoryAdapters.ItemHolder itemHolder, int position) {
//        if (position == (categoryArrayList.size() - 1) ) {
//            itemHolder.footerview.setVisibility(View.GONE);
//        } else {
//            itemHolder.footerview.setVisibility(View.VISIBLE);
//        }
//    }

    @Override
    public int getItemCount() {
        return rankingArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private LinearLayout llCategoryMain;
        private TextView txtCategory;
        private View footerview;

        public ItemHolder(View view) {
            super(view);
            llCategoryMain = (LinearLayout) view.findViewById(R.id.llCategoryMain);
            txtCategory = (TextView) view.findViewById(R.id.txtCategory);
            footerview = (View) view.findViewById(R.id.footerview);
            llCategoryMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(v.getTag() != null) {
//                        int position = (int) v.getTag();
//                        ((HeadyActivity) mContext).handleRecyclerClick(position);
//                    }
                }
            });
        }

    }
}

