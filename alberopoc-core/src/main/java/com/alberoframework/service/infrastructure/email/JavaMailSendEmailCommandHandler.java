package com.alberoframework.service.infrastructure.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedVoidCommandHandler;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;

public class JavaMailSendEmailCommandHandler extends AbstractSimpleAuthenticatedVoidCommandHandler<SendEmailCommand> implements SendEmailCommandHandler {
	
	
	private String host;
	private Integer port;
	private String username;
	private String password;
	private String from; //default from (could be overriden by the command?)
	private String fromName; //default fromName (could be overriden by the command?)
	private boolean tls = false;
	
	public JavaMailSendEmailCommandHandler(String host, Integer port, String username, String password, boolean tls, String from,
			String fromName) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.tls = tls;
		this.from = from;
		this.fromName = fromName;
	}
	
	public JavaMailSendEmailCommandHandler(String host, Integer port, String username, String password, String from,
			String fromName) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.from = from;
		this.fromName = fromName;
	}

	@Override
	protected void doHandle(SendEmailCommand command, ContextualizedQueryGateway queryGateway,
			ContextualizedCommandGateway commandGateway) {
		
		final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        if (tls) {
        	props.put("mail.smtp.starttls.enable", "true");
        }
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
		
        try {
        	final Message msg = new MimeMessage(session);
            
        	msg.setFrom(new InternetAddress(from, fromName));
        	
            for (String addr : command.getTo()) {
                msg.addRecipient(RecipientType.TO, new InternetAddress(addr));
            }
            
            for (String addr : command.getCc()) {
                msg.addRecipient(RecipientType.CC, new InternetAddress(addr));
            }
            
            for (String addr : command.getBcc()) {
                msg.addRecipient(RecipientType.BCC, new InternetAddress(addr));
            }
            
            msg.setSubject(command.getSubject());
            
            msg.setContent(command.getContent());
            
            Transport.send(msg);
        }
        catch (Exception e) {
        	throw new RuntimeException(e);
        }
        
        
	}

}
