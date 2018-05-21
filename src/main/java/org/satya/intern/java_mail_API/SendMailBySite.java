package org.satya.intern.java_mail_API;

import java.io.IOException;
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendMailBySite {  
	public static void main(String[] args) throws IOException {  
		final Properties  prop = new Properties();
		prop.load(new SendMailBySite().getClass().getClassLoader().getResourceAsStream("config.resources"));
  
		//Get the session object  
		Properties props = new Properties();  
		props.put("mail.smtp.host", prop.getProperty("HOST_SERVER"));  
		props.put("mail.smtp.auth", "true");  
     
		Session session = Session.getDefaultInstance(props,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(prop.getProperty("USER_NAME"), prop.getProperty("PASSWORD"));  
			}  
		});  
  
		//Compose the message  
		try {  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(prop.getProperty("USER_NAME")));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(prop.getProperty("PASSWORD")));  
			message.setSubject("javatpoint");  
			message.setText("This is simple program of sending email using JavaMail API");  
       
			//send the message  
			Transport.send(message);  
			
			System.out.println("message sent successfully...");  
   
		}
		catch (MessagingException e) {e.printStackTrace();}  
	}  
}  