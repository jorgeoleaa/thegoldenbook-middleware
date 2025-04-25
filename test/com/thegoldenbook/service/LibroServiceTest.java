package com.thegoldenbook.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Autor;
import com.thegoldenbook.model.LibroDTO;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Tematica;
import com.thegoldenbook.service.LibroCriteria;
import com.thegoldenbook.service.LibroService;
import com.thegoldenbook.service.impl.LibroServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class LibroServiceTest {

	private static Logger logger = LogManager.getLogger(LibroServiceTest.class);
	private LibroService libroService = null; 

	public LibroServiceTest() {
		libroService = new LibroServiceImpl();
	}

	public void testFindByLibro() throws Exception{

		logger.traceEntry("Testing findByLibroId...");
		LibroDTO l = null;
		String locale="it";
		l = libroService.findByLibro(locale, 1l);

		if(l == null) {
			logger.trace("No se ha encontrado ningún libro con el ID proporcionado");
		}
		else {
			logger.info(l);
		}
	}
	
	public void testFindByCriteriaNombre() throws Exception{

		logger.traceEntry("Testing findByCriteria...");

		LibroCriteria c = new LibroCriteria();
		c.setNombre("a");
		String locale = "it";
		c.setLocale(locale);
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 1);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se ha encontrado ningún libro con el ID proporcionado");
		}else {
			for(LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}

	}

	public void testFindByCriteriaId() throws Exception{

		logger.traceEntry("Testing findByCriteria...");

		LibroCriteria c = new LibroCriteria();
		c.setId(15l);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 1);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se ha encontrado ningún libro con el ID proporcionado");
		}else {
			for(LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}

	}

	public void testFindByCriteraIsbn() throws Exception{

		logger.traceEntry("Testing FindByCriteriaIsbn...");

		LibroCriteria c = new LibroCriteria();
		c.setIsbn("978-0060913076");
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se ha encontrado ningún libro con el ISBN proporcionado");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByPrecioDesde() throws Exception{
		logger.traceEntry("Testing FindByCriteriaPrecioDesde...");

		LibroCriteria c = new LibroCriteria();
		c.setDesdePrecio(25.00);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que tengan un precio superior o igual al proporcionado");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}

	}

	public void testFindByPrecioHasta() throws Exception{
		logger.traceEntry("Testing FindByCriteriaPrecioHasta...");

		LibroCriteria c = new LibroCriteria();
		c.setHastaPrecio(15.00);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros con un precio inferior o igual al proporcionado");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaUnidadesDesde() throws Exception{
		logger.traceEntry("Testing FindByCriteriaUnidadesDesde");

		LibroCriteria c = new LibroCriteria();
		c.setUnidadesDesde(12);
		c.setOrderBy(LibroCriteria.ORDER_BY_UNIDADES);
		c.setAscDesc(Boolean.TRUE);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.error("No se han encontrado libros que tengan más unidades o las mismas que las proporcionadas");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaUnidadesHasta() throws Exception{
		logger.traceEntry("Testing findByCriteriaUnidadesHasta...");

		LibroCriteria c = new LibroCriteria();
		c.setUnidadesHasta(5);
		c.setOrderBy(LibroCriteria.ORDER_BY_UNIDADES);
		c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No hay libros que tengan menos unidades o las mismas que las proporcionadas");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaFechaDesde() throws Exception{
		logger.traceEntry("Testing findByCriteriaFechaDesde...");

		LibroCriteria c = new LibroCriteria();
		c.setDesdeFecha(DateUtils.getDate(1960, 01, 1));
		c.setOrderBy(LibroCriteria.ORDER_BY_FECHA);
		c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que hayan sido publicados posteriormente a la fecha proporcionada");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaFechaHasta() throws Exception{
		logger.traceEntry("Testing findByCriteriaFechaHasta...");

		LibroCriteria c = new LibroCriteria();
		c.setHastaFecha(DateUtils.getDate(1950, 0, 1));
		c.setOrderBy(LibroCriteria.ORDER_BY_FECHA);
		c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que hayan sido publicados anteriormente de la fecha proporcionada");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaGeneroLiterarioId() throws Exception{
		logger.traceEntry("Testing findByCriteriaGeneroLiterarioId...");

		LibroCriteria c = new LibroCriteria();
		c.setGeneroLiterarioId(1);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que pertenezcan al género literario proporcionado");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaClasificacionEdadId() throws Exception{
		logger.traceEntry("Testing findByCriteriaClasificacionEdadId...");

		LibroCriteria c = new LibroCriteria();
		c.setClasificacionEdadId(1);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que pertenezcan a la clasificación por edad proporcionada");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaIdiomaId() throws Exception{
		logger.traceEntry("Testing findByCriteriaIdiomaId...");

		LibroCriteria c = new LibroCriteria();
		c.setIdiomaId(2);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que estén escritos en el idioma proporcionado");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testfindByCriteriaFormatoId() throws Exception{
		logger.traceEntry("Testing findByCriteriaFormatoId...");

		LibroCriteria c = new LibroCriteria();
		c.setFormatoId(3);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que estén disponibles en el formato proporcionado");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaByMultipleParameters() throws Exception{
		logger.traceEntry("Testing findByCriteriaByMultipleParameterss...");

		LibroCriteria c = new LibroCriteria();
		//c.setFormatoId(4);
		//c.setIdiomaId(1);
		c.setDesdeFecha(DateUtils.getDate(1950, 1, 1));
		c.setHastaFecha(DateUtils.getDate(1970, 1, 1));
		//c.setHastaPrecio(20.00);
		//c.setDesdePrecio(18.00);
		//c.setUnidadesDesde(5);
		//c.setUnidadesHasta(9);
		//c.setId(4l);
		//c.setNombre("978-0141182605");
		//c.setOrderBy(c.ORDER_BY_UNIDADES);
		//c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<LibroDTO> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que concuerden con los parametros de búsqueda proporcionados");
		}else {
			for (LibroDTO l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaWithoutParameters() throws Exception{
		logger.traceEntry("Testing testFindByCriteriaWithoutParameters...");
		LibroCriteria c = new LibroCriteria();
		String locale = "it";
		Results<LibroDTO> libros = libroService.findByCriteria(c, 1, 5);

		for (LibroDTO l : libros.getPage()) {
			logger.info(l);
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");


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
		l.setSinopsis("Esta colección de relatos del maestro de la ciencia ficción Ray Bradbury te transporta a un futuro imaginario donde la humanidad ha colonizado Marte. A través de una serie de cuentos interconectados, Bradbury explora temas como la colonización, la soledad, la nostalgia y la naturaleza humana. Desde la llegada inicial de los humanos a Marte hasta su eventual abandono, las \"Crónicas Marcianas\" te sumergen en un mundo extraordinario lleno de maravillas y peligros, mientras reflexiona sobre la condición humana y el destino de nuestra especie en el vasto cosmos.");
		l.setPrecio(9.45);
		l.setClasificacionEdadId(2);
		l.setGeneroLiterarioId(2);
		l.setIdiomaId(1);
		l.setFormatoId(2);
		l.setFechaPublicacion(DateUtils.getDate(1901, 0, 1));
		l.setAutores(autores);
		l.setTematicas(tematicas);

		libroService.create(locale, l);
		if(l.getId()!= null) {
			logger.info("El libro con ID = "+l.getId()+" ha sido guardado correctamente.");
		}else {
			logger.info("No se ha creado correctamente");
		}
	}
	
	public void update() throws Exception{

		logger.traceEntry("Testing delete...");
		String locale = "it";
		LibroDTO l = libroService.findByLibro(locale, 1l);
		//l.setValoracionMedia(4.75);
		l.setClasificacionEdadId(3);
		
		if(libroService.update(l)) {
			logger.info("Los datos del libro con ID = "+l.getId()+" actualizados correctamente.");
		}else {
			logger.info("Los datos no se han actualizado correctamente");
		}
	}

	public static void main(String[] args) throws Exception{
		LibroServiceTest test = new LibroServiceTest();
		test.testFindByLibro();
		//test.testFindByCriteriaId();
//		test.testFindByCriteriaNombre();
		//test.testFindByCriteraIsbn();
		//test.testFindByPrecioDesde();
		//test.testFindByPrecioHasta();
		//test.testFindByCriteriaUnidadesDesde();
		//test.testFindByCriteriaUnidadesHasta();
		//test.testFindByCriteriaFechaDesde();
		//test.testFindByCriteriaFechaHasta();
		//test.testFindByCriteriaGeneroLiterarioId();
		//test.testFindByCriteriaIdiomaId();
		//test.testfindByCriteriaFormatoId();
		//test.testFindByCriteriaByMultipleParameters();
		//test.testFindByCriteriaWithoutParameters();
		//test.testCreate();
		//test.update();
		
	}

}
