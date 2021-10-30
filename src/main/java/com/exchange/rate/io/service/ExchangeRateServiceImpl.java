package com.exchange.rate.io.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.exchange.rate.io.model.ExchangeRate;
import com.exchange.rate.io.model.ExchangeRateResponse;
import com.exchange.rate.io.repository.ExchangeRateRepository;

@Component
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Value("${exchange.rate.io.accessKey}")
	String accessKey;

	@Autowired
	ExchangeRateRepository exchangeRateRepository;

	@Override
	public String loadDataToDb() {

		List<String> allDates = getDateList();

		for (String dates : allDates) {
			String exchangeRateUrl = "http://api.exchangeratesapi.io/v1/" + dates + "?access_key=" + accessKey
					+ "&symbols=GBP,USD,HKD";
			RestTemplate restTemplate = new RestTemplate();
			ExchangeRateResponse result = restTemplate.getForObject(exchangeRateUrl, ExchangeRateResponse.class);
			System.out.println("#####result" + result);

			ExchangeRate rateObject = new ExchangeRate();
			rateObject.setDate(result.getDate());
			rateObject.setBase(result.getBase());
			rateObject.setGbp(result.getRates().getGbp());
			rateObject.setHkd(result.getRates().getHkd());
			rateObject.setUsd(result.getRates().getUsd());

			exchangeRateRepository.save(rateObject);
		}

		return "Loaded data into DB";
	}

	public List<String> getDateList() {

		List<String> allDates = new ArrayList<>();
		String maxDate = "2021-10-01";
		SimpleDateFormat monthDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(monthDate.parse(maxDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 1; i <= 12; i++) {
			String month_name1 = monthDate.format(cal.getTime());
			allDates.add(month_name1);
			cal.add(Calendar.MONTH, -1);
		}
		System.out.println(allDates);

		return allDates;

	}

	@Override
	public String getExchangeRate(String date, String symbol) {

		System.out.println(date + " " + symbol);
		System.out.println("lowercase" + symbol.toLowerCase());
		String exchangeRate = null;

		if (symbol.toLowerCase().equals("gbp")) {
			exchangeRate = exchangeRateRepository.getExchangeRateFordateAndGbpSymbol(date);
			System.out.println(exchangeRate + " " + symbol);
		} else if (symbol.toLowerCase().equals("hkd")) {
			exchangeRate = exchangeRateRepository.getExchangeRateFordateAndHkdSymbol(date);
			System.out.println(exchangeRate + " " + symbol);
		} else if (symbol.toLowerCase().equals("usd")) {
			exchangeRate = exchangeRateRepository.getExchangeRateFordateAndUsdSymbol(date);
			System.out.println(exchangeRate + " " + symbol);
		}

		return exchangeRate;
	}

	@Override
	public List<ExchangeRate> getAllData() {

		return exchangeRateRepository.findAll();
	}

}
