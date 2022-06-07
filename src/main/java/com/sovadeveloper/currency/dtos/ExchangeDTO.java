package com.sovadeveloper.currency.dtos;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class ExchangeDTO {
    private String disclaimer;
    private String license;
    private Date timestamp;
    private String base;
    private HashMap<String, Double> rates;


}
