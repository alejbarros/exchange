package com.foreing.exchange.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.foreing.exchange.market.controller")
public class SpringForeignExchangeMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringForeignExchangeMarketApplication.class, args);
	}
}
