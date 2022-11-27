package br.com.rmf.ordersystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rmf.ordersystemapi.entities.DemandItem;

@Repository
public interface DemandItemRepository extends JpaRepository<DemandItem, Integer> {

}
