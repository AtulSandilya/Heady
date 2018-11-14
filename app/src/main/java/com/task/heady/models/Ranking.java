package com.task.heady.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ranking {

    @SerializedName("ranking")
    private String ranking;
    @SerializedName("products")
    private List<RankingProduct> products = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<RankingProduct> getProducts() {
        return products;
    }

    public void setProducts(List<RankingProduct> products) {
        this.products = products;
    }

}
