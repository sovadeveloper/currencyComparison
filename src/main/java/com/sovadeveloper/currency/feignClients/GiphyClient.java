package com.sovadeveloper.currency.feignClients;

import com.sovadeveloper.currency.dtos.GiphyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphyClient", url = "${giphy.api.url}")
public interface GiphyClient {
    @GetMapping("/random")
    GiphyDTO getRandom(@RequestParam(name = "api_key") String apiKey,
                       @RequestParam(name = "tag") String tag);
}
