package com.sovadeveloper.currency.props;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClientsProps {
    @Value("${exchange.api.key}")
    private String exchangeApiKey;

    @Value("${giphy.api.key}")
    private String giphyApiKey;

    @Value("${exchange.currency.base}")
    private String base;
}
