package com.foreing.exchange.market.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.foreing.exchange.market.domain.Country;

@RestController
public class CountryController {
        
	   private static final Logger logger = LoggerFactory.getLogger(CountryController.class);	
   	
	   @Value("${endPoint.country}")	
	   private String endPoint;
	   
	   @RequestMapping("/country")
	   public @ResponseBody Country  getCountries() {
			RestTemplate restTemplate = new RestTemplate();
			logger.info("call the endPoit:" + endPoint);
			Country country = restTemplate.getForObject(endPoint, Country.class);	        
			logger.info("successful response endPoint" + Country.class);
			return country;
	    }
	
	
}
