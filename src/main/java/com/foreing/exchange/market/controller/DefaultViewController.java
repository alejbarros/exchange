package com.foreing.exchange.market.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foreing.exchange.market.domain.Country;
import com.foreing.exchange.market.domain.CountryData;
import com.foreing.exchange.market.domain.ExchangeData;
import com.foreing.exchange.market.sort.SortCountry;

@Controller
public class DefaultViewController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultViewController.class);	
    private String currencyIdDefault  = "USD";
    
    @Value("${country.top}")	
	 private String countryTop;
	
    @Autowired
	CountryController countryService;
	
	@Autowired
	ConvertController convertService;
	
	@GetMapping("/")
    public String home(Model model) {
		newHome("",model);
        return "/home.html";
    }
	
	@GetMapping("/home")
    public String newHome(Model model) {
		newHome("",model);
        return "/home";
    }
	
	@RequestMapping("/home/{convertion}")
    public String newHome(@PathVariable("convertion") String currencyId, Model model) {
		Double ret = 0.0;	
    	getContriesData(model,currencyId);
    	model.addAttribute("result", ret);
        return "/home";
    }
   
	private void getContriesData(Model model, String currencyId) {
		if (currencyId.equals("") || currencyId == "" || currencyId == null) currencyId = currencyIdDefault;
		Country countries = countryService.getCountries();
		List<CountryData> countriesRates = new ArrayList<CountryData>();
    	List<CountryData> countriesData = new ArrayList<CountryData>();    	 
    	ExchangeData exchangeData = new ExchangeData();    	
    	getCountries(currencyId, countries, countriesData); 
    	countriesRates = getTopCounties(countriesData );
    	model.addAttribute("countries", countriesData);
    	model.addAttribute("countriesRate", countriesRates); 	
    	model.addAttribute("exchangeData", exchangeData);
	}

	private void getCountries(String countryCurrencyId, Country countries, List<CountryData> countriesData) {
		for (Entry<String, CountryData> entry : countries.getResults().entrySet()) {
			CountryData country = entry.getValue();
			countriesData.add(country);
		}
        Collections.sort(countriesData, new SortCountry());
        List<CountryData> countriesTop =  getTopCounties(countriesData );
        for(CountryData country :countriesTop) {
        	if(!country.getCurrencyId().equals(countryCurrencyId)) {
        		String convert = countryCurrencyId + "_" + country.getCurrencyId();
        		Double rate = convertService.convert(convert);
        		country.setRate(String.valueOf(rate));
        	} else {
        		country.setRate("1");
        	}
        	
        }
	}
		
	private List<CountryData> getTopCounties(List<CountryData> countriesData ) {
		String [] countriesTop = countryTop.split(",");
		List<CountryData> countriesRates = new ArrayList<CountryData>();
		for(String countryName: countriesTop ) {
			CountryData countryTop = findCountry(countryName,countriesData);
			countriesRates.add(countryTop);
		}
		return countriesRates;
	}
	//implement binary search after
	private CountryData findCountry(String countryName , List<CountryData> countriesData) {
		CountryData ret = null;
		for(CountryData country : countriesData) {
			if(country.getName().toLowerCase().equals(countryName.toLowerCase())) {
				ret = country;
				break;
			}
		}
		return ret;
	}
		
}
