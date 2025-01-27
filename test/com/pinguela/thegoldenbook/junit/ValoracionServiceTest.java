package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.model.ValoracionDTO;
import com.pinguela.thegoldenbook.service.ValoracionCriteria;
import com.pinguela.thegoldenbook.service.ValoracionService;
import com.pinguela.thegoldenbook.service.impl.ValoracionServiceImpl;
import com.pinguela.thegoldenbook.util.DateUtils;

public class ValoracionServiceTest {

	private ValoracionService valoracionService = null;

	public ValoracionServiceTest() {
		valoracionService = new ValoracionServiceImpl();
	}

	@Test
	public void testFindByValoracion1() throws Exception{
		ValoracionDTO v = valoracionService.findByValoracion(1l, 8l);
		assertNotNull(v);
	}

	@Test
	public void testFindByValoracion02() throws Exception{
		ValoracionDTO v = valoracionService.findByValoracion(2l, 10l);
		assertNull(v);
	}

	@Test
	public void testFindByCliente01() throws Exception{
		Results<ValoracionDTO> resultados = valoracionService.findByCliente(2l, 1, Integer.MAX_VALUE);
		for(ValoracionDTO v : resultados.getPage()) {
			assertTrue(v.getClienteId() == 2, "Valoraciones que pertenecen al cliente con ID = 2");
		}
	}

	@Test
	public void testFindByCliente02() throws Exception{
		Results<ValoracionDTO> resultados = valoracionService.findByCliente(100l, 1, Integer.MAX_VALUE);
		assertTrue(resultados.getPage().isEmpty(), "No hay valoraciones asociadas a ese cliente");
	}

	@Test
	public void testFindByLibro01() throws Exception{
		Results<ValoracionDTO> resultados = valoracionService.findByLibro(8l, 1, Integer.MAX_VALUE);
		for(ValoracionDTO v : resultados.getPage()) {
			assertTrue(v.getLibroId() == 8, "Valoraciones asociadas al libro con id = 8");
		}
	}

	@Test
	public void testFindByLibro02() throws Exception{
		Results<ValoracionDTO> resultados = valoracionService.findByLibro(100l, 1, Integer.MAX_VALUE);
		assertTrue(resultados.getPage().isEmpty(), "No se han encontrado valoraciones para el libro con id = 100");
	}

	@Test
	public void testFindByCriteriaClienteId() throws Exception{
		ValoracionCriteria valoracion = new ValoracionCriteria();
		valoracion.setClienteId(3l);
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(valoracion, 1, Integer.MAX_VALUE);

		for(ValoracionDTO v : resultados.getPage()) {
			assertTrue(v.getClienteId() == 3, "Valoraciones que realizadas por el cliente con id = 3");

		}
	}

	@Test
	public void testFindByCriteriaLibroId() throws Exception{
		ValoracionCriteria valoracion = new ValoracionCriteria();
		valoracion.setLibroId(4l);
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(valoracion, 1, Integer.MAX_VALUE);

		for(ValoracionDTO v : resultados.getPage()) {
			assertTrue(v.getLibroId() == 4, "Valoraciones que pertenecen al libro con id = 4");
		}
	}

	@Test
	public void testFindByCriteriaFechaDesde() throws Exception{
		ValoracionCriteria c = new ValoracionCriteria();
		c.setFechaDesde(DateUtils.getDate(2023, 8, 25));
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(c, 1, Integer.MAX_VALUE);

		for(ValoracionDTO v : resultados.getPage()) {
			Date fechaPublicacion = v.getFechaPublicacion();
			Date fechaLimite = DateUtils.getDate(2023, 8, 25);

			assertTrue(fechaPublicacion.after(fechaLimite) || fechaPublicacion.equals(fechaLimite),"La fecha de publicación debe ser posterior o igual al 25/8/2023");
		}
	}

	@Test
	public void testFindByCriteriaFechaHasta() throws Exception{
		ValoracionCriteria c = new ValoracionCriteria();
		c.setFechaHasta(DateUtils.getDate(2023, 2, 20));
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(c, 1, Integer.MAX_VALUE);

		for(ValoracionDTO v : resultados.getPage()) {
			Date fechaPublicacion = v.getFechaPublicacion();
			Date fechaLimite = DateUtils.getDate(2023, 2, 20);

			assertTrue(fechaPublicacion.before(fechaLimite) || fechaPublicacion.equals(fechaLimite),"La fecha de publicación debe ser inferior o igual al 20/2/2023");
		}
	}

	@Test
	public void testFindByCriteriaPalabra() throws Exception{
		ValoracionCriteria c = new ValoracionCriteria();
		c.setPalabra("interesante");
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(c, 1, Integer.MAX_VALUE);
		
		assertTrue(!resultados.getPage().isEmpty(), "Valoraciones que en su contenido contienen la palabra interesante");
	}	
	
	@Test
	public void testFindByCriteriaMultipleParameters() throws Exception{
		ValoracionCriteria criteria = new ValoracionCriteria();
		criteria.setFechaDesde(DateUtils.getDateTime(2023, 6, 1, 0, 0, 0)); 
		criteria.setFechaHasta(DateUtils.getDateTime(2023, 8, 25, 0, 0, 0));
		
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(criteria, 1, Integer.MAX_VALUE);
		
		for(ValoracionDTO v : resultados.getPage()) {
			Date fechaPublicacion = v.getFechaPublicacion();
			Date fechaDesde = DateUtils.getDateTime(2023, 6, 1, 0, 0, 0);
			Date fechaHasta = (DateUtils.getDateTime(2023, 8, 25, 0, 0, 0));
			
			assertTrue(fechaPublicacion.before(fechaHasta) && fechaPublicacion.after(fechaDesde),"Valoración publicada enter dos fechas concretas");
		}
	
	}
	
	@Test
	public void findByEmptyCriteria() throws Exception{
		ValoracionCriteria criteria = new ValoracionCriteria();
		Results<ValoracionDTO> resultados = valoracionService.findByValoracionCriteria(criteria, 1, Integer.MAX_VALUE);
		assertEquals(10, resultados.getTotal());
	}
	
	@Test
	public void testCreate() throws Exception{
		ValoracionDTO v = new ValoracionDTO();
		v.setClienteId(2l);
		v.setLibroId(1l);
		v.setNumeroEstrellas(4.2d);
		v.setAsunto("Una buena lectura");
		v.setCuerpo("Un libro muy interesante!");
		v.setFechaPublicacion(new Date());
		valoracionService.create(v);
		assertNotNull(valoracionService.findByValoracion(2l, 1l));
	}
	
	@Test
	public void testUpdate() throws Exception{
		ValoracionDTO v = valoracionService.findByValoracion(1l, 8l);
		v.setCuerpo("holaaa");
		boolean tf = valoracionService.update(v);
		assertTrue(tf);
	}
	
	@Test
	public void testDelete() throws Exception{
		boolean tf = valoracionService.delete(2l, 1l);
		assertTrue(tf);
	}
	
	
}