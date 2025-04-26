package com.thegoldenbook.service;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Order;

public interface MailService {
		
	public void enviar(String para, String asunto, String msg)
		throws MailException;

	public void sendBienvenida(String to, User cliente) throws MailException;
	
	public void sendPedidoRealizado(String to, User cliente, Order pedido) throws MailException ;
}
