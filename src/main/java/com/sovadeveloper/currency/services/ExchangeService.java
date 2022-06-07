package com.sovadeveloper.currency.services;

import com.sovadeveloper.currency.dtos.GiphyDTO;

public interface ExchangeService {
    GiphyDTO currencyComparison(String currencySymbols);
}
