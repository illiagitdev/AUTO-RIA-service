package com.illia.riasurfing.service;

import com.illia.riasurfing.common.RequestServiceConfig;
import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import com.illia.riasurfing.helpers.RequestServiceHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RequestServiceConfig.class})
public class CustomRequestServiceTest {

    @MockBean
    private CustomRequestRepository requestRepository;

    @Autowired
    public CustomRequestService requestService;

    @Test
    public void testGetUserUdsWithSubscriptionReturnEmptyList(){
        //given
        when(requestRepository.getAllWithSubscription(true)).thenReturn(List.of());
        //when
        final List<Integer> userUdsWithSubscription = requestService.getUserUdsWithSubscription(true);
        //then
        assertThat(userUdsWithSubscription.size()).isEqualTo(0);
    }

    @Test
    public void testGetUserUdsWithSubscriptionReturnListOfUserId(){
        //given
        when(requestRepository.getAllWithSubscription(true))
                .thenReturn(RequestServiceHelper.getSubscriptionsList2());
        //when
        final List<Integer> userUdsWithSubscription = requestService.getUserUdsWithSubscription(true);
        //then
        assertThat(userUdsWithSubscription.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteByTimeCreatedBefore(){
        //given
        long age = 12121212;
        //when
        requestService.deleteByTimeCreatedBefore(age);
        //then
        verify(requestRepository, times(1)).deleteByTimeCreatedBefore(eq(age));
    }

    @Test
    public void testGetAllSubscriptionForUserReturnEmptyList(){
        //given
        int userId = 1;
        Pageable of = Pageable.unpaged();
        when(requestRepository.findAllByUserIdAndSubscriptionIsTrue(userId, of))
                .thenReturn(Page.empty(of));
        //when
        final Page<CustomRequest> allSubscriptionForUser =
                requestService.getAllSubscriptionForUser(userId, Pageable.unpaged());
        //then
        assertThat(allSubscriptionForUser.getTotalElements()).isEqualTo(0);
    }

    @Test
    public void testGetAllSubscriptionForUserReturnList(){
        //given
        int userId = 1;
        Pageable of = Pageable.unpaged();
        when(requestRepository.findAllByUserIdAndSubscriptionIsTrue(userId, of))
                .thenReturn(RequestServiceHelper.getSubscriptionsForUserList2(userId, of));
        //when
        final Page<CustomRequest> allSubscriptionForUser =
                requestService.getAllSubscriptionForUser(userId, of);
        //then
        assertThat(allSubscriptionForUser.getTotalElements()).isEqualTo(2);
    }

    @Test
    public void testGetAllSubscriptionForUserReturnEmptySortedList(){
        //given
        int userId = 1;
        Pageable of = PageRequest.of(1, 2, Sort.by("id").ascending());
        when(requestRepository.findAllByUserIdAndSubscriptionIsTrue(userId, of))
                .thenReturn(RequestServiceHelper.getSubscriptionsForUserList2(userId, of));
        //when
        final Page<CustomRequest> allSubscriptionForUser =
                requestService.getAllSubscriptionForUser(userId, of);
        //then
        assertThat(allSubscriptionForUser.getSize()).isEqualTo(2);
        assertThat(allSubscriptionForUser.getSort()).isEqualTo(Sort.by("id"));
    }

}
