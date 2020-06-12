package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.GearBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearboxRepository extends JpaRepository<GearBox, Integer> {

    @Query("FROM GearBox WHERE category_id=?1")
    List<GearBox> findByCategory(int categoryId);

    boolean existsByValue(int gearboxId);

}
