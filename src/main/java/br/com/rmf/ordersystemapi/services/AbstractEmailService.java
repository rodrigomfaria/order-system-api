package br.com.rmf.ordersystemapi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.rmf.ordersystemapi.entities.Demand;

public abstract class AbstractEmailService implements EmailService {

	@Value("${mail.default.sender}")
	private String sender;

	@Override
	public void sendDemandConfirmationEmail(Demand obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);

	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Demand obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCostumer().getEmail());
		sm.setFrom(sender);
		sm.setSubject("confirmed demand!!!" + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
