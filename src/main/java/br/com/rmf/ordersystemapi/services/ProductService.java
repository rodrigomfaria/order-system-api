package br.com.rmf.ordersystemapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.entities.Category;
import br.com.rmf.ordersystemapi.entities.Product;
import br.com.rmf.ordersystemapi.repositories.CategoryRepository;
import br.com.rmf.ordersystemapi.repositories.ProductRepository;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String direction,
			String orderby) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		List<Category> categories = categoryRepository.findAllById(ids);
		return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);

	}

}
