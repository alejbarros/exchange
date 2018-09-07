package com.foreing.exchange.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages ={"com.foreing.exchange.market.controller","com.foreing.exchange.market.service"})
@EntityScan("com.foreing.exchange.market.domain")
@EnableJpaRepositories("com.foreing.exchange.market.repo")
public class SpringForeignExchangeMarketApplication {
	
		
	public static void main(String[] args) {
		SpringApplication.run(SpringForeignExchangeMarketApplication.class, args);
	}
}
