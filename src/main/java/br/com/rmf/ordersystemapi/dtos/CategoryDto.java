package br.com.rmf.ordersystemapi.dtos;

import java.io.Serializable;

import br.com.rmf.ordersystemapi.entities.Category;

public class CategoryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	public CategoryDto() {

	}

	public CategoryDto(Category obj) {
		id = obj.getId();
		name = obj.getName();
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

}
