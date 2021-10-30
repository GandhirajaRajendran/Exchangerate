package com.exchange.rate.io.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exchange.rate.io.model.ExchangeRate;

@Service
public interface ExchangeRateService {
	
	public String loadDataToDb();

	public String getExchangeRate(String date, String symbol);

	public List<ExchangeRate> getAllData();
}
