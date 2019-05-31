package br.com.fiap.am.coopfit.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.am.coopfit.services.EmailService;
import br.com.fiap.am.coopfit.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}