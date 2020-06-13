package com.illia.riasurfing.service;

import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomRequestServiceImpl implements CustomRequestService {
    private CustomRequestRepository requestRepository;

    @Autowired
    public CustomRequestServiceImpl(CustomRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Integer> getUserUdsWithSubscription(boolean isSubscribed) {
        return requestRepository.getAllWithSubscription(isSubscribed);
    }

    @Override
    public void deleteByTimeCreatedBefore(long time) {
        requestRepository.deleteByTimeCreatedBefore(time);
    }

    @Override
    public Page<CustomRequest> getAllSubscriptionForUser(Integer userId, Pageable of) {
        return requestRepository.findAllByUserIdAndSubscriptionIsTrue(userId, of);
    }

}
