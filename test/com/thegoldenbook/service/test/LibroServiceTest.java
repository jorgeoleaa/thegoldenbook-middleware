package com.thegoldenbook.service.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Author;
import com.thegoldenbook.model.Book;
import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Subject;
import com.thegoldenbook.service.BookCriteria;
import com.thegoldenbook.service.BookService;
import com.thegoldenbook.service.impl.BookServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class LibroServiceTest {

	private static Logger logger = LogManager.getLogger(LibroServiceTest.class);
	private BookService libroService = null; 

	public LibroServiceTest() {
		libroService = new BookServiceImpl();
	}

	public void testFindByLibro() throws Exception{

		logger.traceEntry("Testing findByLibroId...");
		Book l = null;
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

		BookCriteria c = new BookCriteria();
		c.setNombre("a");
		String locale = "it";
		c.setLocale(locale);
		Results<Book> resultados = libroService.findByCriteria(c, 1, 1);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se ha encontrado ningún libro con el ID proporcionado");
		}else {
			for(Book l : resultados.getPage()) {
				logger.info(l);
			}
		}

	}

	public void testFindByCriteriaId() throws Exception{

		logger.traceEntry("Testing findByCriteria...");

		BookCriteria c = new BookCriteria();
		c.setId(15l);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 1);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se ha encontrado ningún libro con el ID proporcionado");
		}else {
			for(Book l : resultados.getPage()) {
				logger.info(l);
			}
		}

	}

	public void testFindByCriteraIsbn() throws Exception{

		logger.traceEntry("Testing FindByCriteriaIsbn...");

		BookCriteria c = new BookCriteria();
		c.setIsbn("978-0060913076");
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se ha encontrado ningún libro con el ISBN proporcionado");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByPrecioDesde() throws Exception{
		logger.traceEntry("Testing FindByCriteriaPrecioDesde...");

		BookCriteria c = new BookCriteria();
		c.setDesdePrecio(25.00);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que tengan un precio superior o igual al proporcionado");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}

	}

	public void testFindByPrecioHasta() throws Exception{
		logger.traceEntry("Testing FindByCriteriaPrecioHasta...");

		BookCriteria c = new BookCriteria();
		c.setHastaPrecio(15.00);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros con un precio inferior o igual al proporcionado");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaUnidadesDesde() throws Exception{
		logger.traceEntry("Testing FindByCriteriaUnidadesDesde");

		BookCriteria c = new BookCriteria();
		c.setUnidadesDesde(12);
		c.setOrderBy(BookCriteria.ORDER_BY_UNIDADES);
		c.setAscDesc(Boolean.TRUE);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.error("No se han encontrado libros que tengan más unidades o las mismas que las proporcionadas");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaUnidadesHasta() throws Exception{
		logger.traceEntry("Testing findByCriteriaUnidadesHasta...");

		BookCriteria c = new BookCriteria();
		c.setUnidadesHasta(5);
		c.setOrderBy(BookCriteria.ORDER_BY_UNIDADES);
		c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No hay libros que tengan menos unidades o las mismas que las proporcionadas");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaFechaDesde() throws Exception{
		logger.traceEntry("Testing findByCriteriaFechaDesde...");

		BookCriteria c = new BookCriteria();
		c.setDesdeFecha(DateUtils.getDate(1960, 01, 1));
		c.setOrderBy(BookCriteria.ORDER_BY_FECHA);
		c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que hayan sido publicados posteriormente a la fecha proporcionada");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaFechaHasta() throws Exception{
		logger.traceEntry("Testing findByCriteriaFechaHasta...");

		BookCriteria c = new BookCriteria();
		c.setHastaFecha(DateUtils.getDate(1950, 0, 1));
		c.setOrderBy(BookCriteria.ORDER_BY_FECHA);
		c.setAscDesc(Boolean.FALSE);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que hayan sido publicados anteriormente de la fecha proporcionada");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}

	public void testFindByCriteriaGeneroLiterarioId() throws Exception{
		logger.traceEntry("Testing findByCriteriaGeneroLiterarioId...");

		BookCriteria c = new BookCriteria();
		c.setGeneroLiterarioId(1);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que pertenezcan al género literario proporcionado");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaClasificacionEdadId() throws Exception{
		logger.traceEntry("Testing findByCriteriaClasificacionEdadId...");

		BookCriteria c = new BookCriteria();
		c.setClasificacionEdadId(1);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que pertenezcan a la clasificación por edad proporcionada");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaIdiomaId() throws Exception{
		logger.traceEntry("Testing findByCriteriaIdiomaId...");

		BookCriteria c = new BookCriteria();
		c.setIdiomaId(2);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que estén escritos en el idioma proporcionado");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testfindByCriteriaFormatoId() throws Exception{
		logger.traceEntry("Testing findByCriteriaFormatoId...");

		BookCriteria c = new BookCriteria();
		c.setFormatoId(3);
		String locale = "it";
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que estén disponibles en el formato proporcionado");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaByMultipleParameters() throws Exception{
		logger.traceEntry("Testing findByCriteriaByMultipleParameterss...");

		BookCriteria c = new BookCriteria();
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
		Results<Book> resultados = libroService.findByCriteria(c, 1, 10);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado libros que concuerden con los parametros de búsqueda proporcionados");
		}else {
			for (Book l : resultados.getPage()) {
				logger.info(l);
			}
		}
	}
	
	public void testFindByCriteriaWithoutParameters() throws Exception{
		logger.traceEntry("Testing testFindByCriteriaWithoutParameters...");
		BookCriteria c = new BookCriteria();
		String locale = "it";
		Results<Book> libros = libroService.findByCriteria(c, 1, 5);

		for (Book l : libros.getPage()) {
			logger.info(l);
		}
	}
	
	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");


		List<Author> autores = new ArrayList<Author>();
		List<Subject>tematicas = new ArrayList<Subject>(); 

		Subject t = new Subject();
		t.setId(8);
		tematicas.add(t);
		
		Subject t2 = new Subject();
		t2.setId(2);
		tematicas.add(t2);

		Author a = new Author();
		a.setId(24l);
		autores.add(a);

		String locale = "it";
		
		Book l = new Book();
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
		Book l = libroService.findByLibro(locale, 1l);
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
