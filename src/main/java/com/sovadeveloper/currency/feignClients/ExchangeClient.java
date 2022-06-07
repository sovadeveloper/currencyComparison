package com.sovadeveloper.currency.feignClients;

import com.sovadeveloper.currency.dtos.HistoricalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangeClient", url = "${exchange.api.url}")
public interface ExchangeClient {
    @GetMapping("/historical/{date}.json")
    HistoricalDTO getHistorical(@PathVariable(name = "date") String date,
                                @RequestParam(name = "app_id") String appId,
                                @RequestParam(name = "base", defaultValue = "USD") String base,
                                @RequestParam(name = "symbols", defaultValue = "RUB") String symbols);
}
