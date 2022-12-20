package br.com.rmf.ordersystemapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.rmf.ordersystemapi.services.validation.CostumerInsert;

@CostumerInsert
public class CostumerNewDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "obligatory filling")
	@Length(min = 5, max = 120, message = "the length must be between 5 and 120 characters")
	private String name;

	@NotEmpty(message = "obligatory filling")
	@Email(message = "Invalid Email")
	private String email;

	@NotEmpty(message = "obligatory filling")
	private String registrationNumber;

	private Integer costumerType;

	@NotEmpty(message = "obligatory filling")
	private String publicPlace;

	@NotEmpty(message = "obligatory filling")
	private String number;

	private String complement;

	private String district;

	@NotEmpty(message = "obligatory filling")
	private String zipCode;

	@NotEmpty(message = "obligatory filling")
	private String phoneNumbers1;

	private String phoneNumbers2;

	private String phoneNumbers3;

	private Integer cityId;

	public CostumerNewDto() {

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

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Integer getCostumerType() {
		return costumerType;
	}

	public void setCostumerType(Integer costumerType) {
		this.costumerType = costumerType;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumbers1() {
		return phoneNumbers1;
	}

	public void setPhoneNumbers1(String phoneNumbers1) {
		this.phoneNumbers1 = phoneNumbers1;
	}

	public String getPhoneNumbers2() {
		return phoneNumbers2;
	}

	public void setPhoneNumbers2(String phoneNumbers2) {
		this.phoneNumbers2 = phoneNumbers2;
	}

	public String getPhoneNumbers3() {
		return phoneNumbers3;
	}

	public void setPhoneNumbers3(String phoneNumbers3) {
		this.phoneNumbers3 = phoneNumbers3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
