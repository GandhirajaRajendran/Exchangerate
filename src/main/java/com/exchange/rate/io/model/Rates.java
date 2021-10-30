package com.exchange.rate.io.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor

public class Rates {

	
	   @JsonProperty("GBP") 
	    public double gbp;
	    @JsonProperty("USD") 
	    public double usd;
	    @JsonProperty("HKD") 
	    public double hkd;
	    

		@Override
		public String toString() {
			return "Rates [gbp=" + gbp + ", usd=" + usd + ", hkd=" + hkd + "]";
		}

	    
	    
}
