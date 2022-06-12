package com.sovadeveloper.currency.services;

import com.sovadeveloper.currency.dtos.GiphyDTO;
import com.sovadeveloper.currency.dtos.GiphyData;
import com.sovadeveloper.currency.feignClients.GiphyClient;
import com.sovadeveloper.currency.props.ClientsProps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ExchangeServiceImplTest {

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private ExchangeServiceImpl exchangeService;

    @Autowired
    private ClientsProps clientsProps;

    @Test
    void currencyComparison() {
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("msg", "OK");
        testMap.put("status", "200");
        GiphyDTO giphyDTO = new GiphyDTO(new GiphyData("https://some-url.com"), testMap);
        when(giphyClient.getRandom(clientsProps.getGiphyApiKey(), clientsProps.getBrokeTag()))
                .thenReturn(giphyDTO);
        GiphyDTO giphyDTO1 = exchangeService.currencyComparison(clientsProps.getSymbol());
        Assertions.assertEquals(giphyDTO1, giphyDTO);
    }
}