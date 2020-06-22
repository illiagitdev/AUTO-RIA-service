package com.illia.riasurfing.common;

import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.service.CustomRequestService;
import com.illia.riasurfing.service.CustomRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RequestServiceConfig {

    @Autowired
    public CustomRequestRepository requestRepository;

    @Bean
    public CustomRequestService requestService() {
        return new CustomRequestServiceImpl(requestRepository);
    }
}
