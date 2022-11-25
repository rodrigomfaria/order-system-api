package br.com.rmf.ordersystemapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rmf.ordersystemapi.entities.Category;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

	@RequestMapping(method = RequestMethod.GET)
	public List<Category> toList() {
		
		Category cat1 = new Category(1, "Computing");
		Category cat2 = new Category(2, "Office");
		
		List<Category> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}

}
