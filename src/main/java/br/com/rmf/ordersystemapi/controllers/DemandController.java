package br.com.rmf.ordersystemapi.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rmf.ordersystemapi.entities.Demand;
import br.com.rmf.ordersystemapi.services.DemandService;

@RestController
@RequestMapping(value = "demands")
public class DemandController {

	@Autowired
	private DemandService demandService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Demand> find(@PathVariable Integer id) {
		Demand obj = demandService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Demand obj) {
		obj = demandService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
