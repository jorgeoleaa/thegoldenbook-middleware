package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Region;
import com.thegoldenbook.service.RegionService;
import com.thegoldenbook.service.impl.RegionServiceImpl;

public class RegionServiceTest {
	
	private static Logger logger = LogManager.getLogger(RegionServiceTest.class);
	private RegionService regionService = null;
	
	public RegionServiceTest() {
		regionService = new RegionServiceImpl();
	}
	
	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		List<Region> results = regionService.findAll("es_ES");
		if(results.isEmpty()) {
			logger.trace("There are no results");
		}else {
			for(Region region : results) {
				logger.info(region);
			}	
		}
	}
	
	public void testFindById() throws Exception{
		logger.traceEntry("Testing findByRegionId...");
		Region region = regionService.findById(4, "es_ES");
		
		if(region!=null) {
			logger.info(region);
		}else {
			logger.trace("There are no results");
		}
	}
	
	public static void main(String[]args) throws Exception{
		RegionServiceTest test = new RegionServiceTest();
		test.testFindAll();
		test.testFindById();
	}

}
