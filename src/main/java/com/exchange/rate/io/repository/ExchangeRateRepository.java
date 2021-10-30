package com.exchange.rate.io.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exchange.rate.io.model.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>  {

	@Query("select gbp FROM exchangerate WHERE date=:date")
	public String getExchangeRateFordateAndGbpSymbol(@Param(value = "date") String date);

	@Query("select hkd FROM exchangerate WHERE date=:date")
	public String getExchangeRateFordateAndHkdSymbol(@Param(value = "date") String date);

	@Query("select usd FROM exchangerate WHERE date=:date")
	public String getExchangeRateFordateAndUsdSymbol(@Param(value = "date") String date);

}
