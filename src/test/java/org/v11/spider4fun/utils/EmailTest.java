package org.v11.spider4fun.utils;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;
import org.v11.spider4fun.core.utils.WY163Email;

public class EmailTest {
	
	public void testSend() throws AddressException, MessagingException{
		WY163Email.Send("fjndwy123", "wywy199137", "442629928@qq.com", "hello", "ping you");
	}
	public static void main(String[] args) throws AddressException, MessagingException {
		WY163Email.Send("wow_haigui", "wow123wow", "442629928@qq.com", "",new Date()+"账号变化", "1550");
		
	}
}
