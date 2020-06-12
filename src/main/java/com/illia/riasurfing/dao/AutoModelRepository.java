package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.AutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoModelRepository extends JpaRepository<AutoModel, Integer> {

    @Query("FROM AutoModel WHERE category_id=?1 AND mark_id=?2")
    List<AutoModel> findByCategoryAndMark(int categoryId, int markaId);

    boolean existsByValue(Integer modelId);

}
