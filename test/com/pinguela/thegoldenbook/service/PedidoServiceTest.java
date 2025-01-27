package com.pinguela.thegoldenbook.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pinguela.thegoldenbook.model.LibroDTO;
import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.impl.LibroServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.util.DateUtils;

public class PedidoServiceTest {

	private static Logger logger = LogManager.getLogger(PedidoServiceTest.class);
	private PedidoService pedidoService = null;
	private LibroService libroService = null;

	public PedidoServiceTest() {
		pedidoService = new PedidoServiceImpl();
		libroService = new LibroServiceImpl();
	}

	public void testFindById() throws Exception{
		logger.info("Testing FindById...");
		Pedido p = null;
		pedidoService = new PedidoServiceImpl();
		p = pedidoService.findBy(3l);
		if(p == null){
			logger.info("No se han encontrado resultados a partir del identificador introducido");
		}else {
			logger.info(p);
		}
	}

	public void testFindByCriteriaId() throws Exception{
		logger.info("Testing FindByCriteriaId...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setId(2l);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado resultados a partir del identificador introducido");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}

	public void testFindByCriteriaFechaDesde() throws Exception{
		logger.info("Testing FindByCriteriaFechaDesde...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setFechaDesde(DateUtils.getDate(2023, 9, 1));
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos realizados a partir de la fecha proporcionada");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}

	public void testFindByCriteriaFechaHasta() throws Exception{
		logger.info("Testing FindByCriteriaFechaHasta...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setFechaHasta(DateUtils.getDate(2023, 4, 1));
		criteria.setAscDesc(Boolean.TRUE);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);
		
		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos realizados antes de la fecha proporcionada");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaPrecioDesde() throws Exception{
		logger.info("Testing FindByCriteriaPrecioDesde...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setPrecioDesde(40.00);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos con un precio superior al proporcionado");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaPrecioHasta() throws Exception{
		logger.info("Testing FindByCriteriaPrecioHasta...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setPrecioHasta(20.00);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos con un precio inferior al proporcionado");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaClienteId() throws Exception{
		logger.info("Testing FindByCriteriaClienteId...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setClienteId(2l);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos realizados por el cliente proporcionado");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaTipoEstadoPedidoId() throws Exception{
		logger.info("Testing FindByCriteriaTipoEstadoPedidoId...");
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setTipoEstadoPedidoId(2);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, 7);

		if(resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado pedidos a partir del del estado proporcionado");
		}else {
			for(Pedido p : resultados.getPage()) {			
				logger.info(p);
			}
		}
	}
	
	public void testFindByCriteriaMultipleParameters() throws Exception{
		logger.info("Testing findByCriteriaMultipleParameters...");
		PedidoCriteria criteria = new PedidoCriteria();
		//criteria.setFechaDesde(DateUtils.getDate(2023, 8, 1));
		//criteria.setFechaHasta(DateUtils.getDate(2023, 10, 1));
		criteria.setPrecioDesde(16.0d);
		criteria.setPrecioHasta(20.00);
		//criteria.setTipoEstadoPedidoId(2);
		//criteria.setClienteId(3l);
		//criteria.setOrderBy(criteria.ORDER_BY_PRECIO);
		//criteria.setAscDesc(Boolean.FALSE);
		Results<Pedido> resultados = pedidoService.findByCriteria(criteria, 1, 7);
		
		if (resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado resultados a partir de los parámetros introducidos");
		}else {
			for (Pedido p : resultados.getPage()) {
				logger.info(p);
			}
		}
	
	}
	
	public void testFindByEmptyCriteria() throws Exception{
		logger.info("Testing findByEmptyCriteria...");
		PedidoCriteria criteria = new PedidoCriteria();
		Results<Pedido> resultados = pedidoService.findByCriteria(criteria, 1, 7);
		if (resultados.getPage().isEmpty()) {
			logger.info("No se han encontrado resultados a partir de los parámetros introducidos");
		}else {
			for (Pedido p : resultados.getPage()) {
				logger.info(p);
			}
		}
	
	}
	
	public void testCreate() throws Exception{
		Pedido p = new Pedido ();
		LineaPedido lp1 = new LineaPedido();
		LineaPedido lp2 = new LineaPedido();
		LineaPedido lp3 = new LineaPedido();
		LibroDTO libro = libroService.findByLibro("es", 6l);

		p.setFechaRealizacion(new Date());
		p.setClienteId(11l);
		p.setTipoEstadoPedidoId(2);
		
		

		p.getLineas().add(lp3);
		
		p.setPrecio(pedidoService.calcularPrecio(p));
		
		
//		p.getLineas().add(lp1);
		//p.getLineas().add(lp2);
		

		Long id = pedidoService.create(p);
		
		if(id == null) {
			logger.info("El pedido no se ha creado correctamente");
		}else {
			logger.info("El pedido se ha creado correctamente");
		}
	}

	public void testUpdate() throws Exception{
		logger.info("Testing update...");
		Pedido pedido = pedidoService.findBy(11l);
		pedido.setTipoEstadoPedidoId(3);
		pedido.setClienteId(4l);
		List<LineaPedido> pedidos = new ArrayList<LineaPedido>();
		LineaPedido lp = new LineaPedido();
		lp.setLibroId(3l);
		lp.setPedidoId(3l);
		lp.setPrecio(10.00);
		lp.setUnidades(1);
		pedidos.add(lp);
		pedido.setLineas(pedidos);
		boolean b =	pedidoService.update(pedido);
	
		if(b == false) {
			logger.info("El pedido no se ha actualizado");
		}else {
			logger.info("El pedido se ha actualizado correctamente");
		}
	}
	
	public void testDelete() throws Exception{
		logger.info("Testing delete...");
		Pedido p = new Pedido();
		p.setId(12l);
		pedidoService.delete(p.getId());
		
		if(pedidoService.findBy(p.getId()) == null) {
			logger.info("Pedido eliminado correctamente");
		}else {
			logger.info("No se ha borrado el pedido con ID proporcionado porque no se encuentra en la BD");
		}
		
	}

	public static void main (String[]args) throws Exception{
		PedidoServiceTest test = new PedidoServiceTest();
		//test.testFindById();
		//test.testFindByCriteriaId();
		//test.testFindByCriteriaFechaDesde();
		//test.testFindByCriteriaFechaHasta();
		//test.testFindByCriteriaPrecioDesde();
		//test.testFindByCriteriaPrecioHasta();
		//test.testFindByCriteriaClienteId();
		//test.testFindByCriteriaTipoEstadoPedidoId();
		//test.testFindByCriteriaMultipleParameters();
		//test.testFindByEmptyCriteria();
		test.testCreate();
		//test.testUpdate();
		//test.testDelete();
		
	}

}
