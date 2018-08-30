package com.foreing.exchange.market.sort;

import java.util.Comparator;

import com.foreing.exchange.market.domain.CountryData;

public class SortCountry implements Comparator<CountryData> {

	@Override
	public int compare(CountryData o1, CountryData o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
