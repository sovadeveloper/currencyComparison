package com.sovadeveloper.currency.services;

import com.sovadeveloper.currency.dtos.ExchangeDTO;
import com.sovadeveloper.currency.dtos.GiphyDTO;
import com.sovadeveloper.currency.feignClients.ExchangeClient;
import com.sovadeveloper.currency.feignClients.GiphyClient;
import com.sovadeveloper.currency.props.ClientsProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeServiceImpl implements ExchangeService{
    private static final String richTag = "rich";
    private static final String brokeTag = "broke";

    private final ExchangeClient exchangeClient;
    private final GiphyClient giphyClient;
    private final ClientsProps clientsProps;

    @Override
    public GiphyDTO currencyComparison(String currencySymbols){
        String formattedDateNow = dateFormatter(LocalDate.now());
        String formattedDatePrev = dateFormatter(LocalDate.now().minusDays(1));
        log.info("Получаем текущую стоимость валюты и вчерашнюю...");
        ExchangeDTO exchangeDTONow = exchangeClient.getHistorical
                (formattedDateNow, clientsProps.getExchangeApiKey(), clientsProps.getBase(), currencySymbols);
        ExchangeDTO exchangeDTOPrev = exchangeClient.getHistorical
                (formattedDatePrev, clientsProps.getExchangeApiKey(), clientsProps.getBase(), currencySymbols);
        return gifChoosing(exchangeDTONow, exchangeDTOPrev, currencySymbols);
    }

    private GiphyDTO gifChoosing(ExchangeDTO exchangeDTONow, ExchangeDTO exchangeDTOPrev,
                                 String currencySymbols){
        String tag;
        if(exchangeDTONow.getRates().get(currencySymbols) == null || exchangeDTONow.getRates().get(currencySymbols) == null){
            log.warn("Валюта указана не верно");
            throw new RuntimeException("Валюта указана не верно");
        }
        if(exchangeDTONow.getRates().get(currencySymbols) > exchangeDTOPrev.getRates().get(currencySymbols)){
            tag = richTag;
        }else{
            tag = brokeTag;
        }
        log.info("Выбираем рандомную гифку...");

        return giphyClient.getRandom(clientsProps.getGiphyApiKey(), tag);
    }

    private String dateFormatter(LocalDate date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateFormatter.format(date);
    }
}
