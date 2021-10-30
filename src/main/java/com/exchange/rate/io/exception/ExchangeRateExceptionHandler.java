package com.exchange.rate.io.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExchangeRateExceptionHandler {

	@Value(value = "${data.notfound.exception.message}")
	private String message2;
	@Value(value = "${data.common.exception.message}")
	private String message3;

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> generalException(Exception exception) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", message3);

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = ExchangeRateDataNotFoundException.class)
	public ResponseEntity<Object> exception(ExchangeRateDataNotFoundException exception) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", message2);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
