package br.com.rmf.ordersystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rmf.ordersystemapi.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
