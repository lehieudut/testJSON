package com.example.lehieudut.jsontest.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright © 2016 BAP CO., LTD
 * Created by HieuLe on 05/11/2016.
 */

public class ApiClient {
    private static ApiClient sInstance;
    private ApiService mApiService;

    public synchronized static ApiClient getInstance() {
        if (sInstance == null) {
            sInstance = new ApiClient();
        }
        return sInstance;
    }

    /**
     * Service
     *
     * @return
     */
    public synchronized static ApiService getService() {
        return getInstance().mApiService;
    }

    public void init(ApiConfig config) {
        // init OkHttpClient
//        OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder();
//        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(config.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService=retrofit.create(ApiService.class);
    }

}
