package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.search.request.CustomRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomRequestRepository extends PagingAndSortingRepository<CustomRequest, Integer> {

    Page<CustomRequest> getByUserId(int userId, Pageable p);

    void deleteByTimeCreatedBefore(Long time);

    @Query("SELECT DISTINCT c.userId FROM CustomRequest c WHERE c.subscription=?1")
    List<Integer> getAllWithSubscription(Boolean isSubscribed);

    Page<CustomRequest> findAllByUserIdAndSubscriptionIsTrue(Integer userId, Pageable pageable);

}
