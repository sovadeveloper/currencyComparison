package com.sovadeveloper.currency.services;

import com.sovadeveloper.currency.dtos.GiphyDTO;
import com.sovadeveloper.currency.dtos.GiphyData;
import com.sovadeveloper.currency.feignClients.GiphyClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
class ExchangeServiceImplTest {

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private ExchangeServiceImpl exchangeService;

    @Test
    void currencyComparison() {
        GiphyDTO giphyDTO = new GiphyDTO(new GiphyData(), new HashMap<>());
        when(giphyClient.getRandom("l3OcxcKR6EpN8RuRuBgS2gFnP8JSrQlA", "broke"))
                .thenReturn(giphyDTO);
        GiphyDTO giphyDTO1 = exchangeService.currencyComparison("RUB");
        System.out.println(giphyDTO);
        System.out.println(giphyDTO1);
        Assertions.assertEquals(giphyDTO1, giphyDTO);
    }
}