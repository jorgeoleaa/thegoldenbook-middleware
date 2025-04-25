package com.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.thegoldenbook.model.Autor;
import com.thegoldenbook.model.LibroDTO;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Tematica;
import com.thegoldenbook.service.LibroCriteria;
import com.thegoldenbook.service.LibroService;
import com.thegoldenbook.service.impl.LibroServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class LibroServiceTest {

	private LibroService libroService = null;

	public LibroServiceTest() {
		libroService = new LibroServiceImpl();
	}

	@Test
	public void findByCriteriaNombre() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setNombre("Lolita");
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);
		assertEquals(1, resultados.getTotal());
	}

	@Test
	public void testFindByCriteriaId() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setId(4l);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);
		assertEquals(1, resultados.getTotal());
	}

	@Test
	public void testFindByCriteraIsbn() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setIsbn("978-0060913076");
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);
		assertEquals(1, resultados.getTotal());
	}

	@Test
	public void testFindByPrecioDesde() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setDesdePrecio(25.00);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getPrecio() >= 25, "El precio es mayor de 25.00");
		}
	}

	@Test
	public void testFindByPrecioHasta() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setHastaPrecio(15.00);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getPrecio() <= 15, "El precio es menor de 15.00");
		}
	}

	@Test
	public void testFindByCriteriaUnidadesDesde() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setUnidadesDesde(12);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getUnidades() >= 12, "Hay 12 o más unidades disponibles de ese libro");
		}
	}

	@Test
	public void testFindByCriteriaUnidadesHasta() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setUnidadesHasta(5);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getUnidades() <=5, "Hay 5 o menos unidades disponibles de ese libro");
		}
	}

	@Test
	public void testFindByCriteriaFechaDesde() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setDesdeFecha(DateUtils.getDate(1960, 01, 1));
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			Date fechaPublicacion = l.getFechaPublicacion();
			Date fechaLimite = DateUtils.getDate(1960, 01, 01);

			assertTrue(fechaPublicacion.after(fechaLimite) || fechaPublicacion.equals(fechaLimite),"La fecha de publicación debe ser posterior o igual al 1 de enero de 1960");
		}
	}

	@Test
	public void testFindByCriteriaFechaHasta() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setDesdeFecha(DateUtils.getDate(1950, 0, 1));
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			Date fechaPublicacion = l.getFechaPublicacion();
			Date fechaLimite = DateUtils.getDate(1950, 0, 1);

			assertTrue(fechaPublicacion.after(fechaLimite) || fechaPublicacion.equals(fechaLimite),"La fecha de publicación debe ser anterior o igual al 1 de enero de 1960");
		}
	}

	@Test
	public void testFindByCriteriaGeneroLiterarioId() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setGeneroLiterarioId(1);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getGeneroLiterarioId() == 1, "Libros que pertenecen al género literario con id = 1");
		}
	}

	@Test
	public void testFindByCriteriaClasificacionEdadId() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setClasificacionEdadId(1);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getClasificacionEdadId() == 1, "Libros que pertenecen a la clasificación por edad con id = 1");
		}
	}

	@Test
	public void testFindByCriteriaIdiomaId() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setIdiomaId(1);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getIdiomaId() == 1, "Libros que tienen idioma con id = 1");
		}
	}

	@Test
	public void testFindByCriteriaFormatoId() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setFormatoId(1);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getFormatoId() == 1, "Libros que tienen formato con id = 1");
		}
	}

	@Test
	public void testFindByCriteriaByMultipleParameters() throws Exception{
		LibroCriteria c = new LibroCriteria();
		c.setDesdeFecha(DateUtils.getDate(1940, 0, 1));
		c.setDesdePrecio(20.00);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		for(LibroDTO l : resultados.getPage()) {
			assertTrue(l.getPrecio() > 20.00, "Libros que tienen un precio > 20");
			assertTrue(l.getFechaPublicacion().after(DateUtils.getDate(1940, 0, 1)), "Libros que tienen un precio > 20");
		}
	}

	@Test
	public void testFindByCriteriaWithoutParameters() throws Exception{
		LibroCriteria c = new LibroCriteria();
		Results<LibroDTO> libros = libroService.findByCriteria(c, 1, Integer.MAX_VALUE);

		assertEquals(11, libros.getTotal());
	}

	@Test
	public void testFindById01() throws Exception{
		String locale = "it";
		LibroDTO l = libroService.findByLibro(locale, 3l);
		assertEquals(3, l.getId());
	}

	@Test
	public void testFindById02() throws Exception{
		String locale = "it";
		LibroDTO l = libroService.findByLibro(locale, 200l);
		assertEquals(null, l);
	}

	@Test
	public void testCreate() throws Exception{

		List<Autor> autores = new ArrayList<Autor>();
		List<Tematica>tematicas = new ArrayList<Tematica>(); 

		Tematica t = new Tematica();
		t.setId(8);
		tematicas.add(t);

		Tematica t2 = new Tematica();
		t2.setId(2);
		tematicas.add(t2);

		Autor a = new Autor();
		a.setId(24l);
		autores.add(a);
		
		String locale = "it";

		LibroDTO l = new LibroDTO();
		l.setIsbn("978-8445076538");
		l.setNombre("Crónicas Marcianas");
		l.setUnidades(10);
		l.setPrecio(9.45);
		l.setClasificacionEdadId(2);
		l.setGeneroLiterarioId(2);
		l.setIdiomaId(1);
		l.setFormatoId(2);
		l.setFechaPublicacion(DateUtils.getDate(1901, 0, 1));
		l.setAutores(autores);
		l.setTematicas(tematicas);

		libroService.create(locale, l);

		assertNotNull(libroService.findByLibro(locale, l.getId()));
	}

	@Test 
	public void testUpdate()throws Exception{
		String locale = "it";
		LibroDTO l = libroService.findByLibro(locale, 3l);
		String nombre1 = l.getNombre();
		l.setNombre("CRONICAS MARCIANAS");
		libroService.update(l);

		assertNotEquals(libroService.findByLibro(locale, 3l).getNombre(), nombre1);
	}

}
