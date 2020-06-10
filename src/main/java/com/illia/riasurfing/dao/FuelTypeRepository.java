package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Integer> {
}
