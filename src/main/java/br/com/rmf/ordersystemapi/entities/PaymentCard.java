package br.com.rmf.ordersystemapi.entities;

import javax.persistence.Entity;

@Entity
public class PaymentCard extends Payment {

	private static final long serialVersionUID = 1L;

	private Integer installmentsNumber;

	public PaymentCard() {

	}

	public PaymentCard(Integer id, PaymentStatus paymentStatus, Demand demand, Integer installmentsNumber) {
		super(id, paymentStatus, demand);
		this.installmentsNumber = installmentsNumber;
	}

	public Integer getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(Integer installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

}
