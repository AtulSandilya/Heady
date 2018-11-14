package com.task.heady.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.task.heady.R;
import com.task.heady.activities.HeadyActivity;
import com.task.heady.models.Product;

import java.util.ArrayList;
import java.util.List;

public class RecyclerProductAdapters extends RecyclerView.Adapter<RecyclerProductAdapters.ItemHolder> {

    public Context mContext;
    public ArrayList<Product> productArrayList;
    int height = 0;
    int width = 0;

    public RecyclerProductAdapters(Context context, List<Product> results) {
        this.mContext = context;
        this.productArrayList = (ArrayList<Product>) results;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((HeadyActivity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item, viewGroup, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
        Product product = productArrayList.get(position);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width / 2,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        itemHolder.llLinearMain.setLayoutParams(lp);
        itemHolder.txtProductName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private TextView txtProductName;
        private LinearLayout llLinearMain;

        public ItemHolder(View view) {
            super(view);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            llLinearMain = (LinearLayout) view.findViewById(R.id.llLinearMain);
        }
    }
}

