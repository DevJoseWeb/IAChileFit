package br.com.fiap.am.coopfit.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.domain.Questionario;

public interface EmailService {
	
	void sendQuestionarioConfirmationEmail(Questionario obj);
	
	void sendQuestionarioConfirmationHtmlEmail(Questionario obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Pessoa pesoa, String newPass);
	
}
