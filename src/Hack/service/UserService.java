package Hack.service;
import Hack.db.buyer;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

public class UserService {

	public static void sendUserRegisterMessage(buyer s) 
	{
		String to=s.getEmail();
		String subject="New Registration";
		String message="Hi "+s.getName()+"\nWelcome to TribeKart, the best online tribal arts and crafts store.";
		
		String from="tribekart99@gmail.com"; 
		String password="tribedone"; // give ur passsword 
		
		try {
			//Authentication with Gmail server
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			
			Session session = Session.getInstance(props, auth);
			
			//Composing the message
			MimeMessage msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			
			//Sending message
			Transport.send(msg);
			System.out.println("Message delivered successfully , Check your mail ...........");
			//response.sendRedirect("mail.jsp?msg=mail delivered");
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public static String sendUserOTP(String email) 
	{
		
		String to=email;
		int length=4;
		String subject="Email Verification";
		String passOtp = otp();
		String message="Hi your one time password is "+passOtp;
		
		
		String from="tribekart99@gmail.com"; 
		String password="tribedone"; // give ur passsword 
		
		try {
			//Authentication with Gmail server
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			
			Session session = Session.getInstance(props, auth);
			
			//Composing the message
			MimeMessage msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			
			//Sending message
			Transport.send(msg);
			System.out.println("Message delivered successfully , Check your mail ...........");
			//response.sendRedirect("mail.jsp?msg=mail delivered");
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		return passOtp;
	}

	public static String otp() 
	{
		int length=4;
		Random rand=new Random();
		String numbers="0123456789";
		char otp[]=new char[length];
		for (int i = 0; i <length; i++) 
		{
			otp[i]=numbers.charAt(rand.nextInt(numbers.length()));
			System.out.println(otp[i]);
			
		}
		String otp1 = String.valueOf(otp);
		return otp1;
	}


	public static void sendUserUpdate(String emailidStudent) 
	{
		
		String to=emailidStudent;
		String subject="Teacher permission Granted";
		String message="Your request for the course has been accepted by the teacher!he can contact you any moment from now on";
		
		String from="tribekart99@gmail.com"; 
		String password="tribedone"; // give ur passsword 
		
		try {
			//Authentication with Gmail server
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			
			Session session = Session.getInstance(props, auth);
			
			//Composing the message
			MimeMessage msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			
			//Sending message
			Transport.send(msg);
			System.out.println("Message delivered successfully , Check your mail ...........");
			//response.sendRedirect("mail.jsp?msg=mail delivered");
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}


	public static void sendDeleteUpdate(String emailid) 
	{
		String to=emailid;
		String subject="Request Rejected";
		String message="Your request for the Teacher has ben rejected";
		
		String from="techstudies4@gmail.com"; 
		String password="techdone"; // give ur passsword 
		
		try {
			//Authentication with Gmail server
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			
			Session session = Session.getInstance(props, auth);
			
			//Composing the message
			MimeMessage msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			
			//Sending message
			Transport.send(msg);
			System.out.println("Message delivered successfully , Check your mail ...........");
			//response.sendRedirect("mail.jsp?msg=mail delivered");
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}


	public static void sendUserOTP1(String buyerid, String otpsending) {
		String to=buyerid;
		int length=4;
		String subject="Email Verification";
		String passOtp = otpsending;
		String message="Hi your one time password is "+passOtp;
		
		
		String from="tribekart99@gmail.com"; 
		String password="tribedone"; // give ur passsword 
		
		try {
			//Authentication with Gmail server
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			
			Session session = Session.getInstance(props, auth);
			
			//Composing the message
			MimeMessage msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			
			//Sending message
			Transport.send(msg);
			System.out.println("Message delivered successfully , Check your mail ...........");
			//response.sendRedirect("mail.jsp?msg=mail delivered");
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	

}
