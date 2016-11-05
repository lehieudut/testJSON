package com.example.lehieudut.jsontest.Base;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright © 2016 BAP CO., LTD
 * Created by HieuLe on 05/11/2016.
 */

//{
//        "id": 1,
//        "name": "A green door",
//        "price": 12.50,
//        "tags": ["home", "green"]
//        }
@Data
@EqualsAndHashCode
public class JsonModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
}
