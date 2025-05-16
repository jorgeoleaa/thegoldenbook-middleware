package com.thegoldenbook.service.test;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.model.Results;
import com.thegoldenbook.model.Review;
import com.thegoldenbook.service.ReviewCriteria;
import com.thegoldenbook.service.ReviewService;
import com.thegoldenbook.service.impl.ReviewServiceImpl;
import com.thegoldenbook.util.DateUtils;

public class ReviewServiceTest {

	private static Logger logger = LogManager.getLogger(ReviewServiceTest.class);
	private ReviewService reviewService = null;

	public ReviewServiceTest() {
		reviewService = new ReviewServiceImpl();
	}

	public void testFindByReview() throws Exception {
	    logger.traceEntry("Testing findByReview...");
	    Review review = reviewService.findByReview(2L, 3L, "en_US");
	    if (review == null) {
	        logger.trace("No reviews were found using the provided data");
	    } else {
	        logger.info(review);
	    }
	}

	public void testFindByCriteriaClienteId() throws Exception{
		logger.traceEntry("Testing findByCriteria...");
		ReviewCriteria valoracion = new ReviewCriteria();
		valoracion.setClienteId(3l);
		Results<Review> resultados = reviewService.findByValoracionCriteria(valoracion, 1, 5);

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
		Results<Review> resultados = reviewService.findByValoracionCriteria(valoracion, 1, 5);

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
		Results<Review> resultados = reviewService.findByValoracionCriteria(c, 1, 5);

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
		Results<Review> resultados = reviewService.findByValoracionCriteria(c, 1, 10);

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
		Results<Review> resultados = reviewService.findByValoracionCriteria(c, 1, 5);

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
		Results<Review> resultados = reviewService.findByValoracionCriteria(c, 1, 5);

		if(resultados.getPage().isEmpty()) {
			logger.trace("No se han encontrado valoraciones que contengan la cadena de texto proporcionada");
		}else {
			for(Review v : resultados.getPage()) {
				logger.info(v);
			}
		}
	}

	public void testFindByEmptyCriteria() throws Exception{
		logger.traceEntry("Tepublic void testFindByReview() throws Exception {\n"
				+ "    logger.traceEntry(\"Testing findByReview...\");\n"
				+ "    Review review = reviewService.findByReview(2L, 3L, \"en_US\");\n"
				+ "    if (review == null) {\n"
				+ "        logger.trace(\"No reviews were found using the provided data\");\n"
				+ "    } else {\n"
				+ "        logger.info(review);\n"
				+ "    }\n"
				+ "}\n"
				+ "sting FindByEmptyCriteria...");
		ReviewCriteria c = new ReviewCriteria();
		Results<Review>valoraciones = reviewService.findByValoracionCriteria(c, 1, 10);

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
		Results<Review> resultados = reviewService.findByCliente(2l, 1, 5);

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
		Results<Review> resultados = reviewService.findByLibro(8l, 1, 5);

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
		reviewService.create(v, locale);
		logger.info("Creada correctamente la valoración: "+v);
	}

	public void testDelete() throws Exception{
		logger.traceEntry("Testing delete...");
		if(reviewService.delete(2l, 1l)) {
			logger.trace("Valoración eliminada correctamente");
		}else {
			logger.trace("La valoración no se ha eliminado correctamente");
		}
	}

	public void testUpdate() throws Exception{
		logger.traceEntry("Testing update...");
		Review v = reviewService.findByValoracion(2l, 1l);
		v.setCuerpo("holaaa");
		//v.setAsunto("No se que acaba de ");
		
		if(reviewService.update(v)) {
			logger.trace("La valoración se ha actualizado correctamente");
		}else {
			logger.trace("La valoración no se ha actualizado");
		}

	}
	
	public void testCalcularMedia() throws Exception{
		Results<Review> valoraciones = reviewService.findByLibro(8l, 1, Integer.MAX_VALUE);
		double valoracionMedia = reviewService.calcularMedia(valoraciones.getPage());
		logger.trace(valoracionMedia);
	}

	public static void main(String [] args) throws Exception{
		ReviewServiceTest test = new ReviewServiceTest();
		test.testFindByReview();
		//test.testFindByCriteriaClienteId();
		//test.testFindByCriteriaLibroId();
		//test.testFindByCriteriaFechaDesde();
		//test.testFindByCriteriaFechaHasta();
		//test.testFindByCriteriaPalabra();
		//test.testFindByCriteriaMultipleParameters();
		//test.testFindByEmptyCriteria();
		//test.testFindByCliente();
		//test.testFindByLibro();
		//test.testCreate();
		//test.testDelete();
		//test.testUpdate();
//		test.testCalcularMedia();
	}
}
