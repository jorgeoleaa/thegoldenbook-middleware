package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Region;
import com.thegoldenbook.service.RegionService;
import com.thegoldenbook.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	
	private RegionService provinciaService = null;
	
	public ProvinciaServiceTest() {
		provinciaService = new ProvinciaServiceImpl();
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Region> provincias = provinciaService.findAll();
		assertEquals(49, provincias.size());
	}
	
	@Test
	public void testFindById01() throws Exception{
		Region p = provinciaService.findById(5);
		assertEquals(5, p.getId());
	}
	
	@Test
	public void testFindById02() throws Exception{
		Region p = provinciaService.findById(100);
		assertEquals(null, p);
	}
}
