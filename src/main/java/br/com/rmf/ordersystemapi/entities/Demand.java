package br.com.rmf.ordersystemapi.entities;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Demand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date createAt;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "demand")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "costumer_id")
	private Costumer costumer;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address deliveryAddress;

	@OneToMany(mappedBy = "id.demand")
	private Set<DemandItem> items = new HashSet<>();

	public Demand() {
	}

	public Demand(Integer id, Date createAt, Costumer costumer, Address deliveryAddress) {
		super();
		this.id = id;
		this.createAt = createAt;
		this.costumer = costumer;
		this.deliveryAddress = deliveryAddress;
	}

	public double getAmount() {
		double sum = 0.0;
		for (DemandItem demandItem : items) {
			sum = sum + demandItem.getSubtotal();
		}
		return sum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Set<DemandItem> getItems() {
		return items;
	}

	public void setItems(Set<DemandItem> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Demand other = (Demand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Demand number: ");
		builder.append(getId());
		builder.append(", Create at: ");
		builder.append(sdf.format(getCreateAt()));
		builder.append(", Costumer: ");
		builder.append(getCostumer().getName());
		builder.append(", Payment Status: ");
		builder.append(getPayment().getPaymentStatus().getDescription());
		builder.append("\nDetails: ");
		for (DemandItem di : getItems()) {
			builder.append(di.toString());
		}
		builder.append(", Total Value: ");
		builder.append(nf.format(getAmount()));
		builder.append("\n");
		return builder.toString();
	}

}
