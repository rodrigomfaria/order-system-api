package br.com.rmf.ordersystemapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.services.validation.CostumerUpdate;

@CostumerUpdate
public class CostumerDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "obligatory filling")
	@Length(min = 5, max = 120, message = "the length must be between 5 and 120 characters")
	private String name;

	@NotEmpty(message = "obligatory filling")
	@Email(message = "Invalid Email")
	private String email;

	public CostumerDto() {

	}

	public CostumerDto(Costumer obj) {
		super();
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
