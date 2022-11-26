package br.com.rmf.ordersystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rmf.ordersystemapi.entities.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {

}
