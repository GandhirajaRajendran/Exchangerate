package com.exchange.rate.io.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.exchange.rate.io.model.ExchangeRate;
import com.exchange.rate.io.service.ExchangeRateService;

@RestController
public class ExchangeRateController {

	@Autowired
	ExchangeRateService exchangeRateService;

	@GetMapping("/load")
	public ResponseEntity<String> getValuesFromrateIo() {
		String message = exchangeRateService.loadDataToDb();
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	@GetMapping("/getExchangeRate")
	public ResponseEntity<String> getExchangeRate(@RequestParam String date, @RequestParam String symbol) {

		String rate = exchangeRateService.getExchangeRate(date, symbol);

		return new ResponseEntity<>("The coversion Rate of " + symbol + " is " + rate + " as base EUR", HttpStatus.OK);

	}

	@GetMapping("/getAllData")
	public ResponseEntity<List<ExchangeRate>> getAllData() {

		List<ExchangeRate> rate = exchangeRateService.getAllData();

		return new ResponseEntity<List<ExchangeRate>>(rate, HttpStatus.OK);

	}

}
