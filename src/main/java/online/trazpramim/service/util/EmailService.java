package online.trazpramim.service.util;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import online.trazpramim.model.UserDataModel;

@Stateless
public class EmailService {

	@Resource(name = "java:jboss/send/gmail")
	private Session session;
	
	public EmailService() {	}
	
	public void send(String destiny, String subject, String body) {
		
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destiny));
			message.setSubject(subject);
			message.setText(body);
			
			Transport.send(message);			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
		
	}
	
	public void sendToList(List<UserDataModel> users, String subject, String body) {
		
		
		for (UserDataModel user: users) {
			try {
				Message message = new MimeMessage(session);
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
				message.setSubject(subject);
				
				body = "Olá, " + user.getName() + "!\n" + body;
				
				message.setText(body);
				
				Transport.send(message);			
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {			
				e.printStackTrace();
			}
		}
		
		
	}
	
}
