package com.foreing.exchange.market.domain;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
	
	LinkedHashMap <String, CountryData> results = new LinkedHashMap<String,CountryData>();

	public LinkedHashMap<String, CountryData> getResults() {
		return results;
	}

	public void setResults(LinkedHashMap<String, CountryData> results) {
		this.results = results;
	}

	
	
	
}
