package br.com.rmf.ordersystemapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

	@RequestMapping(method = RequestMethod.GET)
	public String toList() {
		return "Rest is working!!!";
	}

}
