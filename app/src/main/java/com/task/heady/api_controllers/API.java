package com.task.heady.api_controllers;

import com.task.heady.models.HeadyModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/json")
    Call<HeadyModel> fetchAllListData();
}
