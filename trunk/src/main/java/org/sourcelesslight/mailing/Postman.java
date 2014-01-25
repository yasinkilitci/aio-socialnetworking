package org.sourcelesslight.mailing;

import java.util.Locale;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.sourcelesslight.model.User;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Postman {

	private String from;
	private String to;
	private String subject;

	private MessageSource messageSource;
	private JavaMailSender mailSender;

	public void sendConfirmationMail(User user) throws MessagingException  {

		to = user.getEmail();
		subject = messageSource.getMessage("E001_SBJ", null, Locale.US);

		MimeMessage message = mailSender.createMimeMessage();

		
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(messageSource.getMessage("E001_MSG", new Object[] { user.getFirstname(),
					user.getConfirmationCode().getCode() }, Locale.US));

			// FileSystemResource file = new FileSystemResource("C:\\log.txt");
			// helper.addAttachment(file.getFilename(), file);
			mailSender.send(message);
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

}