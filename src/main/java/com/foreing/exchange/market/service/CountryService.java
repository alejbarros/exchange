package com.foreing.exchange.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

import com.foreing.exchange.market.domain.CountryData;
import com.foreing.exchange.market.repo.CountryRepo;

@Controller
public class CountryService implements ICountryService {

	@Autowired
    private CountryRepo repository;

	@Override
	public List<CountryData> findAll() {	
        List<CountryData> countries = (List<CountryData>) repository.findAll();
        return countries;
	}

	@Override
	public void saveCountry(CountryData country) {
		repository.save(country);		
	}

	@Override
	public void deleteCountry(CountryData country) {
		repository.delete(country);		
	}

	@Override
	public void deleteAllCountries() {
		repository.deleteAll();		
	}

	@Override
	public void saveAll(List<CountryData> countries) {
		repository.saveAll(countries);		
	}

	@Override
	public void addTopCountry(CountryData country) {
		country.setTop("Y");
		repository.save(country);
	}
	
	

	@Override
	public List<CountryData> findTop() {
		List<CountryData> countries = (List<CountryData>) repository.findTop("Y");
        return countries;		
	}

	@Override
	public CountryData getCountryByName(String name) {
		CountryData country = repository.getCountry(name);
		return country;
	}

	@Override
	public void deleteTopCountry(CountryData country) {
		country.setTop("N");
		repository.save(country);		
	}

	

}
