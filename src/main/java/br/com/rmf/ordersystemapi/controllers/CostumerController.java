package br.com.rmf.ordersystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.services.CostumerService;

@RestController
@RequestMapping(value = "costumers")
public class CostumerController {

	@Autowired
	private CostumerService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Costumer obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

}
