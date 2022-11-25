package br.com.rmf.ordersystemapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.entities.Costumer;
import br.com.rmf.ordersystemapi.repositories.CostumerRepository;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class CostumerService {

	@Autowired
	private CostumerRepository repo;

	public Costumer find(Integer id) {
		Optional<Costumer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Costumer.class.getName()));
	}

}
