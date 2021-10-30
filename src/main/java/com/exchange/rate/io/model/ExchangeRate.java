package com.exchange.rate.io.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "exchangerate")
@Table(name = "exchangerate")
@Getter @Setter @NoArgsConstructor
public class ExchangeRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "date")
    public String date;
    
	@Column(name = "base")
    public String base;
    
	@Column(name = "gbp")
    public Double gbp;
    
	@Column(name = "usd")
    public Double usd;
	
	@Column(name = "hkd")
    public Double hkd;

  

}
