package org.satya.intern.java_mail_API;


import java.io.IOException;
import java.util.Properties;
import org.junit.*;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
 public class TestReceiveMail {
	static final Properties prop = new Properties();
	ReadInbox read_mail = new ReadInbox();
	
	@BeforeClass
	public static void before() throws IOException {
		System.out.println("before");
		prop.load(new TestReceiveMail().getClass().getClassLoader().getResourceAsStream("config.properties"));
	}

	@Test
	public void testCreateStore () throws MessagingException, IOException {
		Assert.assertTrue(read_mail.createStore(prop.getProperty("USER_NAME"), prop.getProperty("PASSWORD")));
	}
    
	@Test(expected=AuthenticationFailedException.class)
	public void testCreateStorefailedCase () throws MessagingException, IOException {
		read_mail.createStore(prop.getProperty("USER_NAME"), "wrong_password");
	}
	
	
	@AfterClass
	public static void after() {
		System.out.println("After");
		prop.clear();
		
	}
}
