package com.foreing.exchange.market.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foreing.exchange.market.domain.CountryData;

@Repository
public interface CountryRepo extends CrudRepository<CountryData, Long> {

	@Query(value = "SELECT * FROM COUNTRIES c WHERE c.top = 'Y'" , 
		 nativeQuery = true)
	public List<CountryData> findTop(String str);
	
	@Query(value = "SELECT * FROM COUNTRIES c WHERE c.name  = ?",
			  nativeQuery = true)
	public CountryData getCountry(String name);
	
	
	
}
