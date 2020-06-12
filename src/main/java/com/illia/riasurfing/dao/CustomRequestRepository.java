package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.CustomRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomRequestRepository extends PagingAndSortingRepository<CustomRequest, Integer> {

    Page<CustomRequest> getByUserId(int userId, Pageable p);

}
