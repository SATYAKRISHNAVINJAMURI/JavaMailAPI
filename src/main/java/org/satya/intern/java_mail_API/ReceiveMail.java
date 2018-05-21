package org.satya.intern.java_mail_API;

import java.util.Properties;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.Session;
import javax.mail.Store;  
  
public class ReceiveMail{  
    public static void main(String[] args) {
    	 
        ReceiveMail gmail = new ReceiveMail();
        gmail.read();
 
    }
 
    public void read() {
 
        Properties props = new Properties();
 
        try {
            props.load(new ReceiveMail().getClass().getClassLoader().getResourceAsStream("config.properties"));
            Session session = Session.getDefaultInstance(props, null);
 
            Store store = session.getStore("imaps");
            store.connect(props.getProperty("GMAIL_SMTP_SERVER"), props.getProperty("USER_NAME"), props.getProperty("PASSWORD"));
 
            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();
 
            System.out.println("Total Messages:- " + messageCount);
 
            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");
            
            for (int i = messages.length - 1; i > messages.length - 10; i--) { // Prints first 10 messages.
                System.out.println("Mail Subject:- " + messages[i].getSubject());
            }
 
            inbox.close(true);
            store.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
} 