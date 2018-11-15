package com.task.heady.models;

import com.google.gson.annotations.SerializedName;

public class RankingProduct {

    @SerializedName("id")
    int id;

    @SerializedName("view_count")
    int view_count;

    @SerializedName("order_count")
    int order_count;

    @SerializedName("shares")
    int shares;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

}
