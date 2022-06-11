package com.sovadeveloper.currency.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDTO {
    private Date timestamp;
    private String base;
    private HashMap<String, Double> rates;
}
