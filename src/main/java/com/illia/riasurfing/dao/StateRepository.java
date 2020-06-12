package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    Optional<State> findByValue(Integer stateId);

}
