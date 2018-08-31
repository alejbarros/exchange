package com.foreing.exchange.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.foreing.exchange.market.controller.DefaultViewController;

@SpringBootApplication
@ComponentScan("com.foreing.exchange.market.controller")
public class SpringForeignExchangeMarketApplication {
	
	@Autowired
	DefaultViewController service;
	
	
		
	public static void main(String[] args) {
		SpringApplication.run(SpringForeignExchangeMarketApplication.class, args);
	}
}
