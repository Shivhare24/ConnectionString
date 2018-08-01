package com.smtp.pkg;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;

public class SmtpMain {

	public static void main(String[] args) {
		/*
		 * for using gmail smtp server
		 * enable the pop3
		 * disable 2 step verification
		 * must enable less secure app access "https://support.google.com/accounts/answer/6010255?hl=en"
		 */
		      // Recipent and sender details.
		      String recipient = "";
		      String sender = ""; 
		      String username = "";
		      String password ="";
		 
		      // using host as gmail
		      String host = "smtp.gmail.com";
		 
		      // Getting system properties
		      Properties properties = System.getProperties();
		 
		      // Setting up mail server
		      properties.setProperty("mail.smtp.host", host);
		      properties.setProperty("mail.smtp.starttls.enable", "true");
		      properties.setProperty("mail.from",sender);
		      properties.setProperty("mail.transport.protocol", "smtp");
		      properties.setProperty("mail.smtp.port", "587");
		      properties.setProperty("mail.smtp.auth", "true"); 
		      properties.setProperty("mail.debug", "true");
		 
		      // creating session object to get properties
		      Session session = Session.getDefaultInstance(properties,
		    		  new javax.mail.Authenticator() {
		    	  protected PasswordAuthentication getPasswordAuthentication() {
		    		  return new PasswordAuthentication(username,password);
		    	  }
		      });
		 
		      try
		      {
		         // MimeMessage object.
		         MimeMessage message = new MimeMessage(session);
		 
		         message.setFrom(new InternetAddress(sender));
		 
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		 
		         message.setSubject("This is Suject");
		 
		         message.setText("This is a test mail");
		 
		         Transport transport = session.getTransport("smtp");
		         transport.connect();
		         transport.sendMessage(message,message.getAllRecipients());
		         transport.close();
		         System.out.println("Mail successfully sent");
		      }
		      catch (MessagingException mex) 
		      {
		         mex.printStackTrace();
		      }
		   }
}