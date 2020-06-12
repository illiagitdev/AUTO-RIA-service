package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.BodyStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyStyleRepository extends JpaRepository<BodyStyle, Integer> {

    @Query("FROM BodyStyle WHERE category_id=?1")
    List<BodyStyle> getAllByCategory(int id);

    boolean existsByValue(int value);

}
