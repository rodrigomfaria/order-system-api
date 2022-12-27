package br.com.rmf.ordersystemapi.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.entities.PaymentBillet;

@Service
public class BilletService {
	
	public void fillPaymentWithBillet(PaymentBillet payment, Date momentOfDemand) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(momentOfDemand);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(momentOfDemand);
	}

}
