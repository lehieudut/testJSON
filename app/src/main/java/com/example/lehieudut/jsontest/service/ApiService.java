package com.example.lehieudut.jsontest.service;

import com.example.lehieudut.jsontest.Base.JsonModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Copyright © 2016 BAP CO., LTD
 * Created by HieuLe on 05/11/2016.
 */

public interface ApiService {
    @GET("bins/{id}")
    Call<JsonModel> getListImage(@Path("id") String id);

    @PUT("bins/{id}")
    Call<JsonModel> updateJSON(@Path("id") String id, @Body JsonModel new_json);
}
