package com.thegoldenbook.service.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.ReadingAgeGroup;
import com.thegoldenbook.service.ReadingAgeGroupService;
import com.thegoldenbook.service.impl.ReadingAgeGroupServiceImpl;

public class ReadingAgeGroupServiceTest {

	private static Logger logger = LogManager.getLogger(ReadingAgeGroupServiceTest.class);
	private ReadingAgeGroupService readingAgeGroupService = null;

	public ReadingAgeGroupServiceTest() {
		readingAgeGroupService = new ReadingAgeGroupServiceImpl();
	}

	public void testFindAll() throws Exception{
		logger.traceEntry("Testing findAll...");
		String locale = "es_ES";
		List<ReadingAgeGroup> results = readingAgeGroupService.findAll(locale);
		if(results.isEmpty()) {
			logger.trace("There is no results");
		}else {
			for(ReadingAgeGroup rag : results) {
				logger.info(rag);
			}
		}
	}


	public static void main(String [] args) throws Exception{
		ReadingAgeGroupServiceTest test = new ReadingAgeGroupServiceTest();
		test.testFindAll();
	}
}
