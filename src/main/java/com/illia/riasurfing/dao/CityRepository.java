package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("FROM City WHERE state_id=?1")
    List<City> getAllByState(int id);
}
