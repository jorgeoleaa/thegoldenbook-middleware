package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Country;
import com.thegoldenbook.service.CountryService;
import com.thegoldenbook.service.impl.CountryServiceImpl;

public class CountryServiceTest {
	
	private static Logger logger = LogManager.getLogger(CountryServiceTest.class);
	private CountryService countryService = null;

	public CountryServiceTest() {
		countryService = new CountryServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		List<Country> countries = countryService.findAll("es_ES");
		if(countries.isEmpty()) {
			logger.trace("There are no results");
		}else {
			for(Country country : countries) {
				logger.info(country);
			}
		}
	}
	
	public void testFindById() throws Exception {
		logger.traceEntry("Testing findById...");
		Country country = countryService.findById(1, "es_ES");
		
		if(country == null) {
			logger.trace("There are no results");
		}else {
			logger.info(country);
		}
	}
	
	public static void main (String[]args) throws Exception{
		CountryServiceTest test = new CountryServiceTest();
		//test.testFindAll();
		test.testFindById();
	}
}
