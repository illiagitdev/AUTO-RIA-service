package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.AutoColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<AutoColor, Integer> {
}
