package com.sovadeveloper.currency.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiphyDTO {
    GiphyData data;
    HashMap<String, String> meta;
}
