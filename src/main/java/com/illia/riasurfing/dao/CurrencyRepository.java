package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
