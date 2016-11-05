package com.example.lehieudut.jsontest.service;

import android.content.Context;

import lombok.Builder;
import lombok.Value;

/**
 * Copyright © 2016 BAP CO., LTD
 * Created by HieuLe on 05/11/2016.
 */
@Value
@Builder
public class ApiConfig {
    private Context context;
    private String baseUrl;
}
