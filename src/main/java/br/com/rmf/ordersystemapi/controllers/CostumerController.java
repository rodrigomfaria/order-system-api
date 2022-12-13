package br.com.rmf.ordersystemapi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rmf.ordersystemapi.dtos.CostumerDto;
import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.services.CostumerService;

@RestController
@RequestMapping(value = "costumers")
public class CostumerController {

	@Autowired
	private CostumerService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Costumer> find(@PathVariable Integer id) {
		Costumer obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@Valid @RequestBody CostumerDto objDto, @PathVariable Integer id) {
		Costumer obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CostumerDto>> findAll() {
		List<Costumer> list = service.findAll();
		List<CostumerDto> listDto = list.stream().map(obj -> new CostumerDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CostumerDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Costumer> list = service.findPage(page, linesPerPage, direction, orderBy);
		Page<CostumerDto> listDto = list.map(obj -> new CostumerDto(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
