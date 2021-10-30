package com.exchange.rate.io.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor

public class ExchangeRateResponse {

	
	public boolean success;
	public int timestamp;
    public String base;
    public String date;
    public Rates rates;
    
	@Override
	public String toString() {
		return "ExchangeRateResponse [success=" + success + ", timestamp=" + timestamp + ", base=" + base + ", date="
				+ date + ", rates=" + rates + "]";
	}
    
}
