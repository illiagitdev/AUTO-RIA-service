package com.illia.riasurfing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.common.HttpClientConfig;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponse;
import com.illia.riasurfing.helpers.HttpClientHelper;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Ignore("not configured yet")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {HttpClientConfig.class})
public class HttpClientServiceTest {

    private static MockWebServer mockWebServer;

    @Autowired
    public ObjectMapper mapper;

    @MockBean
    public OkHttpClient httpClient;

    @MockBean
    public OkHttpSearchServiceImpl okHttpSearchService;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
        mockWebServer.getPort());
////        employeeService = new EmployeeService(baseUrl);
    }

    @Test
    public void testSearchById() throws IOException, InterruptedException {
        //given
        String id = "10";
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(mapper
                .writeValueAsString(HttpClientHelper.getIdSearchResponse())));
        //when
        final IdSearchResponse idSearchResponse = okHttpSearchService.searchById(id);
        //then
        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertEquals("GET", recordedRequest.getMethod());
    }


}
