package br.com.rmf.ordersystemapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rmf.ordersystemapi.entities.Demand;
import br.com.rmf.ordersystemapi.repositories.DemandRepository;
import br.com.rmf.ordersystemapi.services.exceptions.ObjectNotFoundException;

@Service
public class DemandService {

	@Autowired
	private DemandRepository repo;

	public Demand find(Integer id) {
		Optional<Demand> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Demand.class.getName()));
	}

}
