package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
