package com.alberoframework.service.infrastructure.email;

import java.util.List;

import javax.mail.internet.MimeMultipart;

import com.alberoframework.component.command.contract.AbstractCommand;
import com.alberoframework.lang.VoidUnit;

public class SendEmailCommand extends AbstractCommand<VoidUnit> {

	private List<String> to;
	private List<String> cc;
	private List<String> bcc;
	private String subject;
	
	private MimeMultipart content;

	public SendEmailCommand(List<String> to, List<String> cc, List<String> bcc, String subject, MimeMultipart content) {
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.content = content;
	}

	public List<String> getTo() {
		return to;
	}

	public List<String> getCc() {
		return cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public MimeMultipart getContent() {
		return content;
	}

}
