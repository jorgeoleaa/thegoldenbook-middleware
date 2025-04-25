package com.thegoldenbook.service;

import com.thegoldenbook.model.ClienteDTO;
import com.thegoldenbook.model.Pedido;

public interface MailService {
		
	public void enviar(String para, String asunto, String msg)
		throws MailException;

	public void sendBienvenida(String to, ClienteDTO cliente) throws MailException;
	
	public void sendPedidoRealizado(String to, ClienteDTO cliente, Pedido pedido) throws MailException ;
}
