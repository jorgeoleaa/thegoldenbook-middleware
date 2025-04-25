package com.thegoldenbook.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thegoldenbook.config.ConfigurationParametersManager;
import com.thegoldenbook.model.ClienteDTO;
import com.thegoldenbook.model.Pedido;
import com.thegoldenbook.service.MailException;
import com.thegoldenbook.service.MailService;

public class MailServiceImpl implements MailService {

	private Logger logger = LogManager.getLogger(MailServiceImpl.class);
	private static String SERVER_NAME = "mail.smtp.host";
	private static String SERVER_PORT = "mail.smtp.port";
	private static String USER = "mail.username";
	private static String PASSWORD = "mail.password";

	public MailServiceImpl() {
	}

	public void enviar(String para, String asunto, String msg )
			throws MailException{

		try {
			Email email = new SimpleEmail();
			email.setHostName(ConfigurationParametersManager.getParameterValue(SERVER_NAME));
			email.setSmtpPort(Integer.valueOf(ConfigurationParametersManager.getParameterValue(SERVER_PORT)));
			email.setAuthenticator(
					new DefaultAuthenticator(
							ConfigurationParametersManager.getParameterValue(USER), 
							ConfigurationParametersManager.getParameterValue(PASSWORD)));
			//email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);
			email.setFrom(ConfigurationParametersManager.getParameterValue(USER));
			email.setSubject(asunto);
			email.setMsg(msg);
			email.addTo(para);
			email.send();

		}catch(EmailException e) {
			logger.error("Enviando email desde "+ConfigurationParametersManager.getParameterValue(USER)+" para "+para+":");
			throw new MailException("Enviando email a "+para, e);
		}
	}

	@Override
	public void sendBienvenida(String to, ClienteDTO cliente) throws MailException {
		String subject = "¡Bienvenido a TheGoldenBook!";

		StringBuilder body = new StringBuilder()
				.append("<html>")
				.append("<head>")
				.append("<meta charset=\"UTF-8\">")
				.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
				.append("<style>")
				.append("body { font-family: Arial, sans-serif; line-height: 1.6; background-color: #121212; color: #FFD700; padding: 20px; }")
				.append("h2 { color: #FFD700; }")
				.append("p { color: #FFFFFF; }")
				.append(".container { max-width: 600px; margin: 0 auto; background-color: #1e1e1e; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5); border: 2px solid #FFD700; }")
				.append(".header { text-align: center; margin-bottom: 20px; }")
				.append(".logo { max-width: 100px; margin-bottom: 10px; }")
				.append("</style>")
				.append("</head>")
				.append("<body>")
				.append("<div class=\"container\">")
				.append("<div class=\"header\">")
				.append("<img src=\"cid:logo\" alt=\"TheGoldenBook Logo\" class=\"logo\">")
				.append("<h2>¡Bienvenido a TheGoldenBook! 📚</h2>")
				.append("</div>")
				.append("<p>¡Hola " + cliente.getNombre() + "!</p>")
				.append("<p>Bienvenido a TheGoldenBook, tu nueva librería en línea donde encontrarás una amplia selección de libros que te inspirarán, emocionarán y cautivarán. Estamos encantados de tenerte como parte de nuestra comunidad de lectores apasionados.</p>")
				.append("<p>En TheGoldenBook, nos dedicamos a ofrecerte la mejor experiencia de compra de libros con un servicio personalizado y una cuidadosa selección de títulos que abarcan desde clásicos intemporales hasta las últimas novedades literarias. Explora nuestro catálogo y descubre tu próxima lectura favorita.</p>")
				.append("<p>Si tienes alguna pregunta o necesitas ayuda, no dudes en ponerte en contacto con nuestro equipo de atención al cliente, quienes estarán encantados de asistirte en cualquier momento.</p>")
				.append("<p>Una vez más, gracias por unirte a nosotros en esta aventura literaria. Esperamos que disfrutes navegando por nuestra tienda en línea y encontrando libros que enriquezcan tu vida.</p>")
				.append("<p>¡Feliz lectura!</p>")
				.append("<p>Saludos cordiales,</p>")
				.append("<p>Jorge Olea</p>")
				.append("<p>Equipo de TheGoldenBook</p>")
				.append("</div>")
				.append("</body>")
				.append("</html>");


		//MimeBodyPart logoPart = new MimeBodyPart();
		//try {
			
			/*
			 * InputStream inputStream = getClass().getResourceAsStream("/icons/logotiendalibrospequeño.jpg");
			 * if (inputStream == null) {
				throw new MailException("No se pudo cargar la imagen desde resources");
			}
			 */
			

			//logoPart.setDataHandler(new DataHandler(new ByteArrayDataSource(inputStream, "image/jpeg")));
//			//logoPart.setHeader("Content-ID", "<logo>");
//
//		} catch (MessagingException | IOException e) {
//			throw new MailException("Error attaching logo", e);
//		}

		Properties props = new Properties();
		props.put("mail.smtp.host", ConfigurationParametersManager.getParameterValue(SERVER_NAME));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", ConfigurationParametersManager.getParameterValue(SERVER_PORT));

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						ConfigurationParametersManager.getParameterValue(USER),
						ConfigurationParametersManager.getParameterValue(PASSWORD)
						);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ConfigurationParametersManager.getParameterValue(USER)));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body.toString(), "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
//			multipart.addBodyPart(logoPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new MailException("Error sending email", e);
		}
	}

	@Override
	public void sendPedidoRealizado(String to, ClienteDTO cliente, Pedido pedido) throws MailException {
		String subject = "";
		StringBuilder body = new StringBuilder();

		int estadoPedido = pedido.getTipoEstadoPedidoId(); 

		if (estadoPedido == 6) {
			subject = "Confirmación de compra en TheGoldenBook (Tienda Física)";
			body.append("<html>")
			.append("<head>")
			.append("<meta charset=\"UTF-8\">")
			.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
			.append("<style>")
			.append("body { font-family: Arial, sans-serif; line-height: 1.6; background-color: #121212; color: #FFD700; padding: 20px; }")
			.append("h2 { color: #FFD700; }")
			.append("p { color: #FFFFFF; }")
			.append(".container { max-width: 600px; margin: 0 auto; background-color: #1e1e1e; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5); border: 2px solid #FFD700; }")
			.append(".header { text-align: center; margin-bottom: 20px; }")
			.append(".logo { max-width: 100px; margin-bottom: 10px; }")
			.append("</style>")
			.append("</head>")
			.append("<body>")
			.append("<div class=\"container\">")
			.append("<div class=\"header\">")
			.append("<img src=\"cid:logo\" alt=\"TheGoldenBook Logo\" class=\"logo\">")
			.append("<h2>¡Compra realizada en TheGoldenBook (Tienda Física)!</h2>")
			.append("</div>")
			.append("<p>¡Hola " + cliente.getNombre() + "!</p>")
			.append("<p>Gracias por realizar tu compra en una de nuestras tiendas física de TheGoldenBook. Esperamos que disfrutes de tu nuevo libro y de tu experiencia con nosotros.</p>")
			.append("<p>Si necesitas alguna recomendación adicional de lectura o tienes alguna pregunta, no dudes en contactar con nosotros.</p>")
			.append("<p>¡Gracias por tu preferencia y esperamos verte de nuevo pronto!</p>")
			.append("<p>Saludos cordiales,</p>")
			.append("<p>Jorge Olea</p>")
			.append("<p>Equipo de TheGoldenBook</p>")
			.append("</div>")
			.append("</body>")
			.append("</html>");

		} else if (estadoPedido == 5) {
			subject = "¡Tu pedido de TheGoldenBook ha sido entregado!";
			body.append("<html>")
			.append("<head>")
			.append("<meta charset=\"UTF-8\">")
			.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
			.append("<style>")
			.append("body { font-family: Arial, sans-serif; line-height: 1.6; background-color: #121212; color: #FFD700; padding: 20px; }")
			.append("h2 { color: #FFD700; }")
			.append("p { color: #FFFFFF; }")
			.append(".container { max-width: 600px; margin: 0 auto; background-color: #1e1e1e; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5); border: 2px solid #FFD700; }")
			.append(".header { text-align: center; margin-bottom: 20px; }")
			.append(".logo { max-width: 100px; margin-bottom: 10px; }")
			.append("</style>")
			.append("</head>")
			.append("<body>")
			.append("<div class=\"container\">")
			.append("<div class=\"header\">")
			.append("<img src=\"cid:logo\" alt=\"TheGoldenBook Logo\" class=\"logo\">")
			.append("<h2>¡Tu pedido de TheGoldenBook ha sido entregado!</h2>")
			.append("</div>")
			.append("<p>¡Hola " + cliente.getNombre() + "!</p>")
			.append("<p>Estamos felices de informarte que tu pedido ha sido entregado con éxito. Esperamos que disfrutes de tu compra y que los libros elegidos enriquezcan tu tiempo de lectura.</p>")
			.append("<p>Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en ponerte en contacto con nosotros.</p>")
			.append("<p>¡Gracias por elegir TheGoldenBook para tus lecturas!</p>")
			.append("<p>Saludos cordiales,</p>")
			.append("<p>Jorge Olea</p>")
			.append("<p>Equipo de TheGoldenBook</p>")
			.append("</div>")
			.append("</body>")
			.append("</html>");

		} else {
			subject = "Estado de tu pedido en TheGoldenBook";
			body.append("<html>")
			.append("<head>")
			.append("<meta charset=\"UTF-8\">")
			.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
			.append("<style>")
			.append("body { font-family: Arial, sans-serif; line-height: 1.6; background-color: #121212; color: #FFD700; padding: 20px; }")
			.append("h2 { color: #FFD700; }")
			.append("p { color: #FFFFFF; }")
			.append(".container { max-width: 600px; margin: 0 auto; background-color: #1e1e1e; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.5); border: 2px solid #FFD700; }")
			.append(".header { text-align: center; margin-bottom: 20px; }")
			.append(".logo { max-width: 100px; margin-bottom: 10px; }")
			.append("</style>")
			.append("</head>")
			.append("<body>")
			.append("<div class=\"container\">")
			.append("<div class=\"header\">")
			.append("<img src=\"cid:logo\" alt=\"TheGoldenBook Logo\" class=\"logo\">")
			.append("<h2>Estado de tu pedido en TheGoldenBook</h2>")
			.append("</div>")
			.append("<p>¡Hola " + cliente.getNombre() + "!</p>")
			.append("<p>Te informamos que tu pedido está en proceso. Estamos trabajando para que pronto puedas disfrutar de tus libros.</p>")
			.append("<p>Si tienes alguna pregunta sobre tu pedido o necesitas asistencia adicional, no dudes en ponerte en contacto con nosotros.</p>")
			.append("<p>Gracias por tu paciencia y confianza en TheGoldenBook.</p>")
			.append("<p>Saludos cordiales,</p>")
			.append("<p>Jorge Olea</p>")
			.append("<p>Equipo de TheGoldenBook</p>")
			.append("</div>")
			.append("</body>")
			.append("</html>");
		}

//		MimeBodyPart logoPart = new MimeBodyPart();
//		try {
//			InputStream inputStream = getClass().getResourceAsStream("/icons/logotiendalibrospequeño.jpg");
//			if (inputStream == null) {
//				throw new MailException("No se pudo cargar la imagen desde resources");
//			}
//
//			logoPart.setDataHandler(new DataHandler(new ByteArrayDataSource(inputStream, "image/jpeg")));
//			logoPart.setHeader("Content-ID", "<logo>");
//
//		} catch (MessagingException | IOException e) {
//			throw new MailException("Error attaching logo", e);
//		}

		Properties props = new Properties();
		props.put("mail.smtp.host", ConfigurationParametersManager.getParameterValue(SERVER_NAME));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", ConfigurationParametersManager.getParameterValue(SERVER_PORT));

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						ConfigurationParametersManager.getParameterValue(USER),
						ConfigurationParametersManager.getParameterValue(PASSWORD)
						);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ConfigurationParametersManager.getParameterValue(USER)));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body.toString(), "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
//			multipart.addBodyPart(logoPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new MailException("Error sending email", e);
		}

	}

}
