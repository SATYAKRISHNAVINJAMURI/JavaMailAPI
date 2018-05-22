package org.satya.intern.java_mail_API;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;  
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;  
  
public class ReadInbox { 
	
	private Properties props = new Properties();
	private Store store;
	private Folder inbox;
	private Message[] messages;
	//Creates Connection between Client and Server 
    public boolean createStore(String user_name, String password) throws MessagingException, IOException {
    	
    	Session session = Session.getDefaultInstance(props, null);
        store = session.getStore("imaps");
        store.connect(props.getProperty("GMAIL_SMTP_SERVER"), user_name, password);
		return true;
    }
    //Get all messages from inbox and Display Content.
    public Message[] getAllMessages() throws MessagingException {
    	inbox = store.getFolder("inbox");
        inbox.open(Folder.READ_ONLY);
        return inbox.getMessages();
        
    }
    //Get No of Messages.
    public long getCount() throws MessagingException {
    	return inbox.getMessageCount();
    }
    //Display All Mails if no specific number is passed as an argument
    public void displayMails(long...no_of_mails) throws MessagingException, IOException {
    	if(no_of_mails.length == 1) {
    		for (int i = messages.length - 1; i > messages.length - no_of_mails[0] - 1; i--) { 
    			System.out.println("From:- " + messages[i].getFrom());
    			System.out.println("--------------------------------------------------------------------------");
                System.out.println("Mail Subject:- " + messages[i].getSubject());
    			System.out.println("--------------------------------------------------------------------------");
    			System.out.println("Message:- " + messages[i].getContent());
    			System.out.println();
    			System.out.println();
            }
    	}
    	else if(no_of_mails.length == 0) {
    		for (int i = messages.length - 1; i > 0; i--) { 
    			System.out.println("From:- " + messages[i].getFrom());
    			System.out.println("--------------------------------------------------------------------------");
                System.out.println("Mail Subject:- " + messages[i].getSubject());
    			System.out.println("--------------------------------------------------------------------------");
    			System.out.println("Message:- " + messages[i].getContent());
    			System.out.println();
    			System.out.println();
            }
    	}
    	else {
    		throw new IllegalArgumentException("Give either 0 or 1 parameter");
    	}
    }
 
    public static void main(String[] args) throws MessagingException {
        try {
			ReadInbox read_mail = new ReadInbox();
			read_mail.props.load(new ReadInbox().getClass().getClassLoader().getResourceAsStream("config.properties")); 
			if(read_mail.createStore(read_mail.props.getProperty("USER_NAME"), read_mail.props.getProperty("PASSWORD"))) {
				read_mail.messages = read_mail.getAllMessages();
				System.out.println("Message Count:" + read_mail.getCount());
				read_mail.displayMails(3);
			}
			else {
				System.out.println("Couldn't Establish Connection with Server");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
 
    }
} 