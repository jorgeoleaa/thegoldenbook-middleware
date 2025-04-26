package com.thegoldenbook.service;

import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Pedido;

public interface MailService {
		
	public void enviar(String para, String asunto, String msg)
		throws MailException;

	public void sendBienvenida(String to, User cliente) throws MailException;
	
	public void sendPedidoRealizado(String to, User cliente, Pedido pedido) throws MailException ;
}
