package com.foreing.exchange.market.controller;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class DefaultView extends WebMvcConfigurerAdapter {
	
	 @Override
	    public void addViewControllers( ViewControllerRegistry registry ) {
	        registry.addViewController( "/" ).setViewName( "/home" );
	        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
	        super.addViewControllers( registry );
	    }

}
