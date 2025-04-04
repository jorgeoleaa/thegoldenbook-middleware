package com.pinguela.thegoldenbook.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.pinguela.thegoldenbook.model.LineaPedido;
import com.pinguela.thegoldenbook.model.Pedido;
import com.pinguela.thegoldenbook.model.Results;
import com.pinguela.thegoldenbook.service.LineaPedidoService;
import com.pinguela.thegoldenbook.service.PedidoCriteria;
import com.pinguela.thegoldenbook.service.PedidoService;
import com.pinguela.thegoldenbook.service.impl.LineaPedidoServiceImpl;
import com.pinguela.thegoldenbook.service.impl.PedidoServiceImpl;
import com.pinguela.thegoldenbook.util.DateUtils;

public class PedidoServiceTest {

	private PedidoService pedidoService = null;
	private LineaPedidoService lineaPedidoService = null;
	
	public PedidoServiceTest() {
		pedidoService = new PedidoServiceImpl();
		lineaPedidoService = new LineaPedidoServiceImpl();
	}

	@Test
	public void testFindById01() throws Exception{
		Pedido p = pedidoService.findBy(4l);
		assertEquals(4, p.getId());
	}

	@Test
	public void testFindById02() throws Exception{
		Pedido p = pedidoService.findBy(100l);
		assertEquals(null, p);
	}

	@Test
	public void testFindByCriteriaId() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setId(2l);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
		assertEquals(1, resultados.getTotal());
	}

	@Test
	public void testFindByCriteriaFechaDesde() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setFechaDesde(DateUtils.getDate(2023, 9, 1));
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);

		for(Pedido p : resultados.getPage()) {
			Date fechaPublicacion = p.getFechaRealizacion();
			Date fechaLimite = DateUtils.getDate(2023, 9, 1);

			assertTrue(fechaPublicacion.after(fechaLimite) || fechaPublicacion.equals(fechaLimite)
					,"La fecha de realización debe de ser igula o posterior a 2023/9/1");
		}
	}

	@Test
	public void testFindByCriteriaFechaHasta() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setFechaHasta(DateUtils.getDate(2023, 4, 1));
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);

		for(Pedido p : resultados.getPage()) {
			Date fechaPublicacion = p.getFechaRealizacion();
			Date fechaLimite = DateUtils.getDate(2023, 4, 1);

			assertTrue(fechaPublicacion.before(fechaLimite) || fechaPublicacion.equals(fechaLimite)
					,"La fecha de realización debe de ser igual o anterior a 2023/4/1");
		}
	}

	@Test
	public void testFindByCriteriaPrecioDesde() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setPrecioDesde(40.00);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);

		for(Pedido p : resultados.getPage()) {
			assertTrue(p.getPrecio()>=40, "Libros con un precio igual o superior a 40 euros");
		}
	}

	@Test
	public void testFindByCriteriaPrecioHasta() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setPrecioHasta(20.00);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);

		for(Pedido p : resultados.getPage()) {
			assertTrue(p.getPrecio()<=40, "Libros con un precio igual o inferior a 20 euros");
		}
	}

	@Test
	public void testFindByCriteriaClienteId() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setClienteId(2l);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
		
		for(Pedido p : resultados.getPage()) {
			assertTrue(p.getClienteId() == 2, "Pedidos realizados por el cliente con ID = 2");
		}
	}
	
	@Test
	public void testFindByCriteriaTipoEstadoPedidoId() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setTipoEstadoPedidoId(2);
		Results<Pedido>resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
		
		for(Pedido p : resultados.getPage()) {
			assertTrue(p.getTipoEstadoPedidoId() == 2, "Pedidos que se encuentran en un estado con ID = 2");
		}
	}
	
	@Test
	public void testFindByCriteriaWithMultipleParameters() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		criteria.setPrecioDesde(15.00);
		criteria.setFechaDesde(DateUtils.getDate(2023, 04, 2));
		criteria.setTipoEstadoPedidoId(2);
		Results<Pedido> resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
		
		for(Pedido p : resultados.getPage()) {
			assertTrue(p.getPrecio() >= 15, "Pedidos con precio superior o igual a 15.00");
			assertTrue(p.getFechaRealizacion().after(DateUtils.getDate(2023, 04, 2)), "Pedidos realizados despues del 2023/04/02");
			assertTrue(p.getTipoEstadoPedidoId() == 2, "Pedidos que su estado se corresponde con el id = 2");
		}
	}
	
	@Test
	public void testFindByEmptyCriteria() throws Exception{
		PedidoCriteria criteria = new PedidoCriteria();
		Results<Pedido> resultados = pedidoService.findByCriteria(criteria, 1, Integer.MAX_VALUE);
		assertEquals(11, resultados.getTotal());
	}
	
	@Test
	public void testCreate() throws Exception{
		Pedido p = new Pedido ();
		LineaPedido lp1 = new LineaPedido();
		LineaPedido lp2 = new LineaPedido();

		p.setFechaRealizacion(new Date());
		p.setPrecio(pedidoService.calcularPrecio(p));
		p.setClienteId(1l);
		p.setTipoEstadoPedidoId(1);

		lp1.setPrecio(12.0d);
		lp1.setUnidades(1);
		lp1.setPedidoId(p.getId());
		lp1.setLibroId(4l);
		
		lp2.setPrecio(20.00);
		lp2.setUnidades(1);
		lp2.setPedidoId(p.getId());
		lp2.setLibroId(4l);

		p.getLineas().add(lp1);
		p.getLineas().add(lp2);

		Long id = pedidoService.create(p);
		
		assertNotNull(id);
	}
	
	@Test
	public void testUpdate() throws Exception{
		
		Pedido pedido = pedidoService.findBy(11l);
		pedido.setTipoEstadoPedidoId(4);
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
		
		assertEquals(true, b);
	}
	
	@Test
	public void testDelete() throws Exception{
		boolean tf = pedidoService.delete(14l);
		assertEquals(true, tf);
	}
	
	@Test 
	public void calcularPrecio() throws Exception{
		Pedido p = pedidoService.findBy(1l);
		Double total = pedidoService.calcularPrecio(p);
		assertEquals(33.489999999999995, total);
	}
}
