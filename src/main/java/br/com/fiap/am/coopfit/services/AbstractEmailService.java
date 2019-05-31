package br.com.fiap.am.coopfit.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.fiap.am.coopfit.domain.Pessoa;
import br.com.fiap.am.coopfit.domain.Questionario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendQuestionarioConfirmationEmail(Questionario obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromQuestionario(obj);
		sendEmail(sm);
	}

	@Override
	public void sendQuestionarioConfirmationHtmlEmail(Questionario obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromQuestionario(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendQuestionarioConfirmationEmail(obj);
		}
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromQuestionario(Questionario obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getPessoa().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Questionario confirmado!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	private MimeMessage prepareMimeMessageFromQuestionario(Questionario obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getPessoa().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Questionario confirmado!");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateQuestionario(obj), true);
		return mimeMessage;
	}

	@Override
	public void sendNewPasswordEmail(Pessoa pessoa, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(pessoa, newPass);
		sendEmail(sm);
	}

	private SimpleMailMessage prepareNewPasswordEmail(Pessoa pessoa, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pessoa.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}

	protected String htmlFromTemplateQuestionario(Questionario obj) {
		Context context = new Context();
		context.setVariable("questionario", obj);
		context.setVariable("agua", obj.getqtdCopoAgua());
		context.setVariable("sentimento", obj.gettipoSentimento());
		return templateEngine.process("email/confirmacaoQuestionario", context);
	}

}
