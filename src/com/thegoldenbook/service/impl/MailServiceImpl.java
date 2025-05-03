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
import com.thegoldenbook.model.User;
import com.thegoldenbook.model.Order;
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

	public void send(String to, String subject, String body)
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
			email.setSubject(subject);
			email.setMsg(body);
			email.addTo(to);
			email.send();

		}catch(EmailException e) {
			logger.error("Sending email from "+ConfigurationParametersManager.getParameterValue(USER)+" to "+to+":");
			throw new MailException("Sending email to "+to, e);
		}
	}

	@Override
	public void sendWelcome(String to, User user) throws MailException {
		
		String subject = "¡Welcome to The Golden Book!";

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
			    .append("<h2>Welcome to TheGoldenBook! 📚</h2>")
			    .append("</div>")
			    .append("<p>Hello " + user.getName() + "!</p>")
			    .append("<p>Welcome to TheGoldenBook, your new online bookstore where you'll find a wide selection of books that will inspire, thrill, and captivate you. We are delighted to have you as part of our community of passionate readers.</p>")
			    .append("<p>At TheGoldenBook, we are dedicated to providing you with the best book-buying experience through personalized service and a carefully curated selection of titles ranging from timeless classics to the latest literary releases. Explore our catalog and discover your next favorite read.</p>")
			    .append("<p>If you have any questions or need assistance, don't hesitate to contact our customer support team, who will be happy to assist you at any time.</p>")
			    .append("<p>Once again, thank you for joining us on this literary adventure. We hope you enjoy browsing our online store and finding books that enrich your life.</p>")
			    .append("<p>Happy reading!</p>")
			    .append("<p>Best regards,</p>")
			    .append("<p>Jorge Olea</p>")
			    .append("<p>TheGoldenBook Team</p>")
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
	public void notifyOrderPlaced (String to, User user, Order order) throws MailException {
		
		String subject = "";
		StringBuilder body = new StringBuilder();

		int estadoPedido = order.getOrderStatusId(); 

		if (estadoPedido == 8) {
		    subject = "Purchase Confirmation at TheGoldenBook (Physical Store)";
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
		    .append("<h2>Purchase Completed at TheGoldenBook (Physical Store)!</h2>")
		    .append("</div>")
		    .append("<p>Hello " + user.getName() + "!</p>")
		    .append("<p>Thank you for making your purchase at one of our physical TheGoldenBook stores. We hope you enjoy your new book and your experience with us.</p>")
		    .append("<p>If you need any further reading recommendations or have any questions, feel free to contact us.</p>")
		    .append("<p>Thank you for your preference, and we look forward to seeing you again soon!</p>")
		    .append("<p>Best regards,</p>")
		    .append("<p>Jorge Olea</p>")
		    .append("<p>TheGoldenBook Team</p>")
		    .append("</div>")
		    .append("</body>")
		    .append("</html>");
		}
		
		else if (estadoPedido == 4) {
		    subject = "Your TheGoldenBook Order Has Been Delivered!";
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
		    .append("<h2>Your TheGoldenBook Order Has Been Delivered!</h2>")
		    .append("</div>")
		    .append("<p>Hello " + user.getName() + "!</p>")
		    .append("<p>We are happy to inform you that your order has been successfully delivered. We hope you enjoy your purchase and that the books you've chosen will enrich your reading time.</p>")
		    .append("<p>If you have any questions or need further assistance, feel free to contact us.</p>")
		    .append("<p>Thank you for choosing TheGoldenBook for your readings!</p>")
		    .append("<p>Best regards,</p>")
		    .append("<p>Jorge Olea</p>")
		    .append("<p>TheGoldenBook Team</p>")
		    .append("</div>")
		    .append("</body>")
		    .append("</html>");

		} else {
			subject = "Your TheGoldenBook Order Status";
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
			.append("<h2>Your TheGoldenBook Order Status</h2>")
			.append("</div>")
			.append("<p>Hello " + user.getName() + "!</p>")
			.append("<p>We would like to inform you that your order is in process. We are working to ensure that you can enjoy your books soon.</p>")
			.append("<p>If you have any questions about your order or need further assistance, please feel free to contact us.</p>")
			.append("<p>Thank you for your patience and trust in TheGoldenBook.</p>")
			.append("<p>Best regards,</p>")
			.append("<p>Jorge Olea</p>")
			.append("<p>TheGoldenBook Team</p>")
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
