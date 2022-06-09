package com.sovadeveloper.currency.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class GiphyDTO {
    GiphyData data;
    HashMap<String, String> meta;
}
