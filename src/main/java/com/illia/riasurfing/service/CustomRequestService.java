package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.search.request.CustomRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomRequestService {

    List<Integer> getUserUdsWithSubscription(boolean isSubscribed);

    void deleteByTimeCreatedBefore(long time);

    Page<CustomRequest> getAllSubscriptionForUser(Integer userId, Pageable of);

}
