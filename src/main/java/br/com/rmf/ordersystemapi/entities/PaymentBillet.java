package br.com.rmf.ordersystemapi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PaymentBillet extends Payment {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@Temporal(TemporalType.DATE)
	private Date paymentDate;

	public PaymentBillet() {
	}

	public PaymentBillet(Integer id, PaymentStatus paymentStatus, Demand demand, Date dueDate, Date paymentDate) {
		super(id, paymentStatus, demand);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
