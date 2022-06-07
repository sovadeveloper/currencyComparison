package com.sovadeveloper.currency.dtos;

import lombok.Data;

import java.util.HashMap;

@Data
public class GiphyDTO {
    GiphyData data;
    HashMap<String, String> meta;
}
