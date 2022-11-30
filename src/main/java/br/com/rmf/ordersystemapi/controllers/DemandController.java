package br.com.rmf.ordersystemapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rmf.ordersystemapi.entities.Demand;
import br.com.rmf.ordersystemapi.services.DemandService;

@RestController
@RequestMapping(value = "demands")
public class DemandController {

	@Autowired
	private DemandService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Demand> find(@PathVariable Integer id) {
		Demand obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

}
