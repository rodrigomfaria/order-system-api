package br.com.rmf.ordersystemapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.dtos.CategoryDto;
import br.com.rmf.ordersystemapi.entities.Category;
import br.com.rmf.ordersystemapi.repositories.CategoryRepository;
import br.com.rmf.ordersystemapi.services.exceptions.DataIntegrityException;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Category> findAll() {
		return repo.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String direction, String orderby) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return repo.findAll(pageRequest);
	}

	public Category fromDto(CategoryDto objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}

}
