package org.satya.intern.java_mail_API;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
class SendEmailByGmail{  
	public void send(final String from,final String password,String to,String sub,String msg) throws IOException{  
		//Get properties object    
		Properties props = new Properties();    
		final Properties prop = new Properties();
		InputStream input = new SendEmailByGmail().getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(input);
		props.put("mail.smtp.host", prop.getProperty("GMAIL_SMTP_SERVER"));    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", prop.getProperty("GMAIL_SMTP_SSL_PORT"));    
		//get Session   
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator(){    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(prop.getProperty("USER_NAME"), prop.getProperty("PASSWORD"));  
			}    
		});    
		//compose message    
		try {    
			MimeMessage message = new MimeMessage(session);    
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
			message.setSubject(sub);    
			message.setText(msg);    
			//send message  
			Transport.send(message);    
			System.out.println("message sent successfully");    
		} 
		catch (MessagingException e) {throw new RuntimeException(e);}    
		
	}
	public static void main(String[] args) throws IOException {
		SendEmailByGmail seg = new SendEmailByGmail();
		Properties prop = new Properties();
		InputStream input = new SendEmailByGmail().getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(input);
		seg.send(prop.getProperty("USER_NAME"), prop.getProperty("PASSWORD"), prop.getProperty("RECIPIENTS"), "Test Mail" ,"Mail Sent through Java API");  
	}
} 