package com.sovadeveloper.currency.services;

import com.sovadeveloper.currency.dtos.GiphyDTO;
import com.sovadeveloper.currency.dtos.HistoricalDTO;
import com.sovadeveloper.currency.feignClients.ExchangeClient;
import com.sovadeveloper.currency.feignClients.GiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ExchangeServiceImpl implements ExchangeService{
    private static final String richTag = "rich";
    private static final String brokeTag = "broke";

    @Value("${exchange.api.key}")
    private String exchangeApiKey;

    @Value("${giphy.api.key}")
    private String giphyApiKey;

    @Value("${exchange.currency.base}")
    private String base;

    private final ExchangeClient exchangeClient;
    private final GiphyClient giphyClient;

    @Autowired
    public ExchangeServiceImpl(ExchangeClient exchangeClient, GiphyClient giphyClient) {
        this.exchangeClient = exchangeClient;
        this.giphyClient = giphyClient;
    }

    @Override
    public GiphyDTO currencyComparison(String currencySymbols){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateNow = LocalDate.now();
        LocalDate datePrev = LocalDate.now().minusDays(1);
        String formattedDateNow = dateFormatter.format(dateNow);
        String formattedDatePrev = dateFormatter.format(datePrev);
        HistoricalDTO historicalDTONow = exchangeClient.getHistorical
                (formattedDateNow, exchangeApiKey, base, currencySymbols);
        HistoricalDTO historicalDTOPrev = exchangeClient.getHistorical
                (formattedDatePrev, exchangeApiKey, base, currencySymbols);
        GiphyDTO giphyDTO = new GiphyDTO();
        System.out.println(historicalDTONow);
        System.out.println(historicalDTOPrev);
        if (historicalDTONow.getRates().get(currencySymbols) > historicalDTOPrev.getRates().get(currencySymbols)){
            giphyDTO = giphyClient.getRandom(giphyApiKey, richTag);
        }else if(historicalDTONow.getRates().get(currencySymbols) < historicalDTOPrev.getRates().get(currencySymbols)){
            giphyDTO = giphyClient.getRandom(giphyApiKey, brokeTag);
        }else{
            // В ТЗ не было, но рассмотрел вариант, что стоимость может не измениться
            System.out.println("Стоимость равна");
        }
        return giphyDTO;
    }
}
