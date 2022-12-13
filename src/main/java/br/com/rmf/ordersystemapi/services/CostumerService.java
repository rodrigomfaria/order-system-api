package br.com.rmf.ordersystemapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.dtos.CostumerDto;
import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.repositories.CostumerRepository;
import br.com.rmf.ordersystemapi.services.exceptions.DataIntegrityException;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class CostumerService {

	@Autowired
	private CostumerRepository repo;

	public Costumer find(Integer id) {
		Optional<Costumer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Tipo: " + Costumer.class.getName()));
	}

	public Costumer update(Costumer obj) {
		Costumer newObj = find(obj.getId());
		UpdateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete because there are related entities");
		}
	}

	public List<Costumer> findAll() {
		return repo.findAll();
	}

	public Page<Costumer> findPage(Integer page, Integer linesPerPage, String direction, String orderby) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderby);
		return repo.findAll(pageRequest);
	}

	public Costumer fromDto(CostumerDto objDto) {
		return new Costumer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	private void UpdateData(Costumer newObj, Costumer obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
