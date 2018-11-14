package com.task.heady.models;

import com.google.gson.annotations.SerializedName;

public class Tax {

    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private Float value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

}
