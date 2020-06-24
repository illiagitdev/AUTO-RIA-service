package com.illia.riasurfing.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.dao.UserRepository;
import com.illia.riasurfing.service.HttpClientService;
import com.illia.riasurfing.service.OkHttpSearchServiceImpl;
import com.illia.riasurfing.service.UserService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@TestConfiguration
public class HttpClientConfig {

    @Bean
    public OkHttpClient clientFactory() {
        return new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    @Bean
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Autowired
    private CustomRequestRepository requestRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @Bean
    public HttpClientService okHttpClient() {
        return new OkHttpSearchServiceImpl(clientFactory(), getMapper(), requestRepository, userService);
    }
}
