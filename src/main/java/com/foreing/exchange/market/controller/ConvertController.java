package com.foreing.exchange.market.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController

public class ConvertController {

	private static final Logger logger = LoggerFactory.getLogger(ConvertController.class);	

	 @Value("${endPoint.convert}")	
	 private String endPoint;
	 
	 @RequestMapping(value ="/convert/{convertion}" , method = RequestMethod.GET)
	 public Double convert(@PathVariable("convertion") String convertion) {
		 RestTemplate restTemplate = new RestTemplate();
	     logger.info("call the endPoit:" + endPoint);   
	     LinkedHashMap <String, Double> results = (LinkedHashMap<String, Double>) restTemplate.getForObject(endPoint+"?q="+convertion+"&compact=ultra", Object.class);	        
		 Double result = results.get(convertion); 
		 logger.info("successful response endPoint :" + result);	
		 return result;
	 }
	 
	 @RequestMapping(value ="/convertModel/{convertion}" )
	 public  Double convertWithModel(@PathVariable("convertion") String convertion) {
		 RestTemplate restTemplate = new RestTemplate();
	     logger.info("call the endPoit:" + endPoint);   
	     LinkedHashMap <String, Double> results = (LinkedHashMap<String, Double>) restTemplate.getForObject(endPoint+"?q="+convertion+"&compact=ultra", Object.class);	        
		 Double result = results.get(convertion); 
		 logger.info("successful response endPoint :" + result);			
		 return result;
	 }

}
