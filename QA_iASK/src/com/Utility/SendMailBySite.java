package com.Utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*; 

public class SendMailBySite {

	public static void sendEmail(String host, String port, final String userName, final String password,
			String toAddress, int code1) throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject("Verification Code");

		msg.setText("You have been registered successfully. \n Your Verification Code is :: " + code1);

		// sends the e-mail
		Transport.send(msg);

	}
	
	public static void sendEmailToFriend(String host, String port, final String userName, final String password,
			String[] toAddress,String question,String URL) throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		InternetAddress[] recipientAddress = new InternetAddress[toAddress.length];
		int counter = 0;
		for (String recipient : toAddress) {
		    recipientAddress[counter] = new InternetAddress(recipient.trim());
		    counter++;
		}
		InternetAddress[] address={new InternetAddress("mohan.kasnet10@outlook.com")};
		msg.setFrom(new InternetAddress(userName));
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setRecipients(Message.RecipientType.BCC, recipientAddress);
		
		String msg1="Dear Commuity User,\n"
				+ "Below Question newly posted into CQA system.\n"
				+ "Kindly click on givan link to give answer.\n '"
				+question+ "'\n"
				+URL+ "\nThank You \n"
						+ "CQA Team.";
		
		msg.setSubject("New Question Posted on iASK in your community");
		msg.setText(msg1);
		Transport.send(msg);
	}

	public static void sendEmailToFriend1(String host, String port, final String userName, final String password,
			String[] toAddress,String question,String URL) throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		InternetAddress[] recipientAddress = new InternetAddress[toAddress.length];
		int counter = 0;
		for (String recipient : toAddress) {
		    recipientAddress[counter] = new InternetAddress(recipient.trim());
		    counter++;
		}
		InternetAddress[] address={new InternetAddress("mohan.kasnet10@outlook.com")};
		msg.setFrom(new InternetAddress(userName));
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setRecipients(Message.RecipientType.BCC, recipientAddress);
		
		String msg1="Dear Commuity User,\n"
				+ "Please give more answer of below question.\n"
				+ "Kindly click on givan link to give answer.\n '"
				+question+ "'\n"
				+URL+ "\nThank You \n"
						+ "CQA Team.";
		
		msg.setSubject("New Question Posted on iASK in your community");
		msg.setText(msg1);
		Transport.send(msg);
	}
	
	public static void sendEmailToFriendAgain(String host, String port, final String userName, final String password,
			String[] toAddress,String question,String URL) throws AddressException, MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		InternetAddress[] recipientAddress = new InternetAddress[toAddress.length];
		int counter = 0;
		for (String recipient : toAddress) {
		    recipientAddress[counter] = new InternetAddress(recipient.trim());
		    counter++;
		}
		InternetAddress[] address={new InternetAddress("mohan.kasnet10@outlook.com")};
		msg.setFrom(new InternetAddress(userName));
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setRecipients(Message.RecipientType.BCC, recipientAddress);
		
		String msg1="Dear Commuity User,\n"
				+ "Below Question again posted into CQA system because of more answer.\n"
				+ "Kindly click on givan link to give more answer.\n '"
				+question+ "'\n"
				+URL+ "\nThank You \n"
						+ "CQA Team.";
		
		
		msg.setSubject("New Question Posted in CQA System");
		msg.setText(msg1);
		Transport.send(msg);
	}
	
	
	public static void main(String[] args) {

	}
}