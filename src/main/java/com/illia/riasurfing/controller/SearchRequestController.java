package com.illia.riasurfing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.entities.search.request.*;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponse;
import com.illia.riasurfing.service.HttpClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Api(value = "Search resource", description = "Search resources")
@RequestMapping(path = "/search")
public class SearchRequestController {
    private HttpClientService httpClientService;
    private ObjectMapper mapper;

    @Autowired
    public SearchRequestController(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @ApiOperation(value = "Return detailed information for post with {id}")
    @GetMapping(path = "/autoId")
    public ResponseEntity<?> params(@RequestParam(value = "id") String autoId) {
        IdSearchResponse response = null;
        try {
            response = httpClientService.searchById(autoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @ApiOperation(value = "Return list of posts")
    @PostMapping(path = "/list")
    public ResponseEntity<?> searchRequest(@RequestBody CustomRequest jsonRequest) {
        return ResponseEntity.ok(mapper.writeValueAsString(httpClientService.searchList(jsonRequest)));
    }
}
