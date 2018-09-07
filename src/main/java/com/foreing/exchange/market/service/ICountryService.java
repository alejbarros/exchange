package com.foreing.exchange.market.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.foreing.exchange.market.domain.CountryData;


public interface ICountryService {

	 public List<CountryData> findAll();
	 	 
	 public List<CountryData> findTop();
	 	 
	 public void saveCountry(CountryData country);
	 
	 public void deleteCountry(CountryData country);
	 
	 public void deleteAllCountries();
	 	 
	 public void saveAll(List<CountryData> countries);
	 
	 public void addTopCountry(CountryData country);
	 
	 public CountryData getCountryByName(String name);
	 
	 public void deleteTopCountry(CountryData country);
	 
}
