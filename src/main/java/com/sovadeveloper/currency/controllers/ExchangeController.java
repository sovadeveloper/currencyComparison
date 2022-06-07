package com.sovadeveloper.currency.controllers;

import com.sovadeveloper.currency.services.ExchangeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {
    private final ExchangeServiceImpl rateService;

    public ExchangeController(ExchangeServiceImpl rateService) {
        this.rateService = rateService;
    }

    @GetMapping("/compare/{currencySymbols}")
    public ResponseEntity getComparison(@PathVariable(name = "currencySymbols") String currencySymbols){
        try {
            return ResponseEntity.ok(rateService.currencyComparison(currencySymbols));
        }catch (RuntimeException e){
            e.printStackTrace();
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
