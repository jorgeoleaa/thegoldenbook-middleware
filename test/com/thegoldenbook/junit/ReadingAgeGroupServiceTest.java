package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.ReadingAgeGroup;
import com.thegoldenbook.service.ReadingAgeGroupService;
import com.thegoldenbook.service.impl.ReadingAgeGroupServiceImpl;

public class ReadingAgeGroupServiceTest {

	private ReadingAgeGroupService readingAgeGroupService = null;
	
	public ReadingAgeGroupServiceTest () {
		readingAgeGroupService = new ReadingAgeGroupServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<ReadingAgeGroup> readingAgeGroups = readingAgeGroupService.findAll("es");
		assertEquals(3, readingAgeGroups.size());
	}

}
