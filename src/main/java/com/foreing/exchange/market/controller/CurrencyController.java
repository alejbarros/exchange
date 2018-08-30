package com.foreing.exchange.market.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.foreing.exchange.market.domain.Currencies;

@RestController

public class CurrencyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);	

	 @Value("${endPoint.currency}")	
	 private String endPoint;
	  
	 @RequestMapping("/currency")
	 public @ResponseBody Currencies  getCurrency() {
		RestTemplate restTemplate = new RestTemplate();
		logger.info("call the endPoit:" + endPoint);
		Currencies currencies = restTemplate.getForObject(endPoint, Currencies.class);	        
		logger.info("successful response endPoint" + Currencies.class);
		
		return currencies;
	 }

}
