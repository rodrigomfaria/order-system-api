package br.com.rmf.ordersystemapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rmf.ordersystemapi.controllers.utils.URL;
import br.com.rmf.ordersystemapi.dtos.ProductDto;
import br.com.rmf.ordersystemapi.entities.Product;
import br.com.rmf.ordersystemapi.services.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = productService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDto>> findPage(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		String nameDecode = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> list = productService.search(nameDecode, ids, page, linesPerPage, direction, orderBy);
		Page<ProductDto> listDto = list.map(obj -> new ProductDto(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
