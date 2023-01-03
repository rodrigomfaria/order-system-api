package br.com.rmf.ordersystemapi.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.rmf.ordersystemapi.entities.Demand;

public interface EmailService {
	
	void sendDemandConfirmationEmail(Demand obj);
	
	void sendEmail(SimpleMailMessage msg);

}
