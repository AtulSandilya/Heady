package com.task.heady.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeadyModel {

    @SerializedName("categories")
    private List<Category> categories = null;
    @SerializedName("rankings")
    private List<Ranking> rankings = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

}
