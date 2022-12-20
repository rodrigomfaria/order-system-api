package br.com.rmf.ordersystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rmf.ordersystemapi.entities.Costumer;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {

	@Transactional(readOnly = true)
	Costumer findByEmail(String email);
}
