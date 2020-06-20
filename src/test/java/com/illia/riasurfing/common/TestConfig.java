package com.illia.riasurfing.common;

import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.dao.UserRepository;
import com.illia.riasurfing.service.UserService;
import com.illia.riasurfing.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
//@ComponentScan(basePackages = {"com.illia.riasurfing.dao"})
public class TestConfig {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CustomRequestRepository requestRepository;

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository, requestRepository);
    }

}
