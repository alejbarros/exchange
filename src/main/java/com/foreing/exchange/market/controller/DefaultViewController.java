package com.foreing.exchange.market.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foreing.exchange.market.domain.Country;
import com.foreing.exchange.market.domain.CountryData;
import com.foreing.exchange.market.domain.ExchangeData;
import com.foreing.exchange.market.service.ICountryService;
import com.foreing.exchange.market.sort.SortCountry;

@Controller
@ComponentScan("com.foreing.exchange.market.service")
public class DefaultViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultViewController.class);	
    private String currencyIdDefault  = "USD";
   
    @Autowired
	CountryController countryService;
	
	@Autowired
	ConvertController convertService;
	
	@Autowired
	private ICountryService countryDataService;
	
	@GetMapping("/")
    public String home(Model model) {
		newHome("",model);
        return "home";
    }
	
	@GetMapping("/home")
    public String newHome(Model model) {
		newHome("",model);
        return "home";
    }
	
	@RequestMapping("/home/{convertion}")
    public String newHome(@PathVariable("convertion") String currencyId, Model model) {
		Double ret = 0.0;	
    	getContriesData(model,currencyId);    	
    	model.addAttribute("result", ret);
        return "home";
    }
	
	@RequestMapping("/add/{name}/{convertion}")
	public String add(@PathVariable("name") String name,@PathVariable("convertion") String convertion, Model model) {
		ExchangeData exchangeData = new ExchangeData();
		List<CountryData> countriesData = countryDataService.findAll(); 
		CountryData countryAdd = countryDataService.getCountryByName(name);  
		countryDataService.addTopCountry(countryAdd);
		List<CountryData> countriesRates =  countryDataService.findTop();
		callServiceRates(convertion, countriesRates);
		sortCollections(model, exchangeData, countriesData, countriesRates);
		return "home";
	}
	
	@RequestMapping("/delete/{name}/{convertion}")
	public String delete(@PathVariable("name") String name,@PathVariable("convertion") String convertion, Model model) {
		ExchangeData exchangeData = new ExchangeData();
		List<CountryData> countriesData = countryDataService.findAll(); 
		CountryData countryDelete = countryDataService.getCountryByName(name);  
		countryDataService.deleteTopCountry(countryDelete);
		List<CountryData> countriesRates =  countryDataService.findTop();
		callServiceRates(convertion, countriesRates);
		sortCollections(model, exchangeData, countriesData, countriesRates);
		return "home";
	}

	private void sortCollections(Model model, ExchangeData exchangeData, List<CountryData> countriesData,
			List<CountryData> countriesRates) {
		Collections.sort(countriesData, new SortCountry());
		Collections.sort(countriesRates, new SortCountry());
		model.addAttribute("countriesRate", countriesRates);
		model.addAttribute("countries", countriesData);
		model.addAttribute("exchangeData", exchangeData);
	}
	
			
	private void getContriesData(Model model, String currencyId) {
		if (currencyId.equals("") || currencyId == "" || currencyId == null) currencyId = currencyIdDefault;
		List<CountryData> countriesData = countryDataService.findAll(); 
		if(countriesData.isEmpty()) {
			Country countries = countryService.getCountries();
		 	getCountries(currencyId, countries, countriesData);
		 	countryDataService.saveAll(countriesData);
		}
		List<CountryData> countriesRates = new ArrayList<CountryData>();   	 
    	ExchangeData exchangeData = new ExchangeData();    	
    	countriesRates = getTopCounties(countriesData );
    	callServiceRates(currencyId, countriesRates);
    	sortCollections(model, exchangeData, countriesData, countriesRates);    	
	}

	private void getCountries(String countryCurrencyId, Country countries, List<CountryData> countriesData) {
		for (Entry<String, CountryData> entry : countries.getResults().entrySet()) {
			CountryData country = entry.getValue();
			countriesData.add(country);
		}
        Collections.sort(countriesData, new SortCountry());
   	}

	private void callServiceRates(String countryCurrencyId, List<CountryData> countriesTop) {
		if (countryCurrencyId.equals("") || countryCurrencyId == "" || countryCurrencyId == null) countryCurrencyId = currencyIdDefault;
		for(CountryData country :countriesTop) {
        	if(!country.getCurrencyId().equals(countryCurrencyId)) {
        		String convert = countryCurrencyId + "_" + country.getCurrencyId();
        		Double rate = convertService.convert(convert);
        		country.setRate(String.valueOf(rate));  
        		countryDataService.addTopCountry(country);
        	} else {
        		country.setRate("1");
        	}
        	
        }
	}
		
	private List<CountryData> getTopCounties(List<CountryData> countriesData ) {
		List<CountryData> countriesRates = countryDataService.findTop();
		return countriesRates;
	}
	
		
}
