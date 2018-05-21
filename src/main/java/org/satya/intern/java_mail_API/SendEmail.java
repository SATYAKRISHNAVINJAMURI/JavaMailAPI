package org.satya.intern.java_mail_API;

import java.io.IOException;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendEmail{  
	public static void main(String [] args) throws IOException{  
		Properties prop = new Properties();
		prop.load(new SendEmail().getClass().getClassLoader().getResourceAsStream("config.properties"));
		//Get the session object  
		Properties properties = System.getProperties();  
		properties.setProperty("mail.smtp.host", "localhost");  
		Session session = Session.getDefaultInstance(properties);  
		
		//compose the message  
		try{  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(prop.getProperty("USER_NAME")));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(prop.getProperty("RECIPIENTS")));  
			message.setSubject("Ping");  
			message.setText("Hello, this is example of sending email  ");  
  
			// Send message  
			Transport.send(message);  
			System.out.println("message sent successfully....");  
  
		}
		catch (MessagingException mex) {mex.printStackTrace();}  
	}  
} 