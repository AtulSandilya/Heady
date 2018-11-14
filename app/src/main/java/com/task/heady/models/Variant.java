package com.task.heady.models;

import com.google.gson.annotations.SerializedName;

public class Variant {

    @SerializedName("id")
    private Integer id;
    @SerializedName("color")
    private String color;
    @SerializedName("size")
    private Object size;
    @SerializedName("price")
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


}
