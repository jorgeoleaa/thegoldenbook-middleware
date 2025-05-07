package com.thegoldenbook.service.test;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ReviewCriteria;
import com.thegoldenbook.service.ReviewService;
import com.thegoldenbook.service.impl.ValoracionServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class ValoracionServiceTest {

	private static Logger logger = LogManager.getLogger(ValoracionServiceTest.class);
	private ReviewService valoracionService = null;

	public ValoracionServiceTest() {
		valoracionService = new ValoracionServiceImpl();
	}

	public void testFindByValoracion() throws Exception{
		logger.traceEntry("Testing findByValoracion...");
		Review v = valoracionService.findByValoracion(1l, 8l);
		if(v == null) {
			logger.trace("No se han encontrado valoraciones a partir de los datos proporcionados");
		}else {
			logger.info(v);
		}	
	}

	public void testFindByCriteriaClienteId() throws Exception{
		logger.traceEntry("Testing findByCriteria...");
		ReviewCriteria valoracion = new ReviewCriteria();
		valoracion.setClienteId(3l);
		Results<Review> resultados = valoracionService.findByValoracionCriteria(valoracion, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado resultados con los parámetros de búsqueda introducidos");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}

		}

	}

	public void testFindByCriteriaLibroId() throws Exception{
		logger.traceEntry("Testing findByCriteria...");
		ReviewCriteria valoracion = new ReviewCriteria();
		valoracion.setLibroId(4l);
		Results<Review> resultados = valoracionService.findByValoracionCriteria(valoracion, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado resultados con los parámetros de búsqueda introducidos");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}

		}

	}


	public void testFindByCriteriaFechaDesde() throws Exception{
		logger.traceEntry("Testing findByCriteriaFechaDesde...");
		ReviewCriteria c = new ReviewCriteria();
		c.setFechaDesde(DateUtils.getDate(2023, 8, 25));
		Results<Review> resultados = valoracionService.findByValoracionCriteria(c, 1, 5);

		if(resultados.getPage() == null) {
			logger.trace("No se han encontrado valoraciones publicadas a partir de la fecha proporcionada");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}

	public void testFindByCriteriaFechaHasta() throws Exception{
		logger.traceEntry("Testing findByCriteriaFechaHasta...");
		ReviewCriteria c = new ReviewCriteria();
		c.setFechaHasta(DateUtils.getDate(2023, 2, 20));
		Results<Review> resultados = valoracionService.findByValoracionCriteria(c, 1, 10);

		if(resultados.getPage() == null) {
			logger.trace("No se han encontrado valoraciones publicadas anteriormente a la fecha proporcionada");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}


	public void testFindByCriteriaPalabra() throws Exception{
		logger.traceEntry("Testing findByCriteriaPalabra...");
		ReviewCriteria c = new ReviewCriteria();
		c.setPalabra("interesante");
		Results<Review> resultados = valoracionService.findByValoracionCriteria(c, 1, 5);

		if(resultados.getPage() == null) {
			logger.trace("No se han encontrado valoraciones que contengan la cadena de texto proporcionada");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}

	public void testFindByCriteriaMultipleParameters() throws Exception{
		logger.traceEntry("Testing findByCriteriaPalabra...");
		ReviewCriteria c = new ReviewCriteria();
		//c.setClienteId(2l);
		//c.setPalabra("interesante");
		c.setFechaDesde(DateUtils.getDateTime(2023, 6, 1, 0, 0, 0)); 
		c.setFechaHasta(DateUtils.getDateTime(2023, 8, 25, 0, 0, 0));
		//c.setLibroId(4l);
		c.setAscDesc(Boolean.TRUE);
		Results<Review> resultados = valoracionService.findByValoracionCriteria(c, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado valoraciones que contengan la cadena de texto proporcionada");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}

	public void testFindByEmptyCriteria() throws Exception{
		logger.traceEntry("Testing FindByEmptyCriteria...");
		ReviewCriteria c = new ReviewCriteria();
		Results<Review>valoraciones = valoracionService.findByValoracionCriteria(c, 1, 10);

		if(valoraciones.getPage().isEmpty()) {
			logger.trace("No se han encontrado resultados");
		}else {
			for(Review v : valoraciones.getPage()) {
				logger.info(v);
			}
		}
	}


	public void testFindByCliente() throws Exception{
		logger.traceEntry("Testing findByClienteId...");
		Results<Review> resultados = valoracionService.findByCliente(2l, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado valoraciones a partir del ID de cliente proporcionado");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}

	public void testFindByLibro() throws Exception{
		logger.traceEntry("Testing findByLibroId...");
		Results<Review> resultados = valoracionService.findByLibro(8l, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado valoraciones a partir del ID del libro proporcionado");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}

	public void testCreate() throws Exception{
		logger.traceEntry("Testing create...");
		
		String locale = "it";
		
		Review v = new Review();
		v.setClienteId(1l);
		v.setLibroId(1l);
		v.setNumeroEstrellas(4.0d);
		v.setAsunto("Una buena lectura");
		v.setCuerpo("Un libro muy interesante!");
		v.setFechaPublicacion(new Date());
		valoracionService.create(v, locale);
		logger.info("Creada correctamente la valoración: "+v);
	}

	public void testDelete() throws Exception{
		logger.traceEntry("Testing delete...");
		if(valoracionService.delete(2l, 1l)) {
			logger.trace("Valoración eliminada correctamente");
		}else {
			logger.trace("La valoración no se ha eliminado correctamente");
		}
	}

	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Review v = valoracionService.findByValoracion(2l, 1l);
		v.setCuerpo("holaaa");
		//v.setAsunto("No se que acaba de ");
		
		if(valoracionService.update(v)) {
			logger.trace("La valoración se ha actualizado correctamente");
		}else {
			logger.trace("La valoración no se ha actualizado");
		}

	}
	
	public void testCalcularMedia() throws Exception{
		Results<Review> valoraciones = valoracionService.findByLibro(8l, 1, Integer.MAX_VALUE);
		double valoracionMedia = valoracionService.calcularMedia(valoraciones.getPage());
		logger.trace(valoracionMedia);
	}

	public static void main(String [] args) throws Exception{
		ValoracionServiceTest test = new ValoracionServiceTest();
		//test.testFindByValoracion();
		//test.testFindByCriteriaClienteId();
		//test.testFindByCriteriaLibroId();
		//test.testFindByCriteriaFechaDesde();
		//test.testFindByCriteriaFechaHasta();
		//test.testFindByCriteriaPalabra();
		//test.testFindByCriteriaMultipleParameters();
		//test.testFindByEmptyCriteria();
		//test.testFindByCliente();
		//test.testFindByLibro();
		test.testCreate();
		//test.testDelete();
		//test.testUpdate();
//		test.testCalcularMedia();
	}
}
