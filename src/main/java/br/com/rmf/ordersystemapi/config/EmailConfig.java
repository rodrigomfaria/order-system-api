package br.com.rmf.ordersystemapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rmf.ordersystemapi.services.EmailService;
import br.com.rmf.ordersystemapi.services.MockEmailService;

@Configuration
public class EmailConfig {

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
