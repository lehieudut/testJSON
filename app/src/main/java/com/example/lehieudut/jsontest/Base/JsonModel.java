package com.example.lehieudut.jsontest.Base;

import java.util.List;

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
@EqualsAndHashCode(callSuper = false)
public class JsonModel {
    private List<Photos> photos;

    @Data
    public  class Photos {
        private String image;
        private String name;
    }
}
