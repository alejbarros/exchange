package com.foreing.exchange.market.domain;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Currencies {
	
	LinkedHashMap <String, CurrencyData> results = new LinkedHashMap<String,CurrencyData>();

	public LinkedHashMap<String, CurrencyData> getResults() {
		return results;
	}

	public void setResults(LinkedHashMap<String, CurrencyData> results) {
		this.results = results;
	}


}
