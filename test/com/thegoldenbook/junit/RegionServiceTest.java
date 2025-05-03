package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Region;
import com.thegoldenbook.service.RegionService;
import com.thegoldenbook.service.impl.RegionServiceImpl;

public class RegionServiceTest {
	
	private RegionService regionService = null;
	
	public RegionServiceTest() {
		regionService = new RegionServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Region> provincias = regionService.findAll("es_ES");
		assertEquals(49, provincias.size());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Region p = regionService.findById(5, "es_ES");
		assertEquals(5, p.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Region p = regionService.findById(100, "es_ES");
		assertEquals(null, p);
	}
}
