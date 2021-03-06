package com.illia.riasurfing.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.entities.ResponseMapper;
import com.illia.riasurfing.entities.search.request.*;
import com.illia.riasurfing.entities.search.response.ApiSearchResponse;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponse;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponseSlim;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class OkHttpSearchServiceImpl implements HttpClientService{
    private static final Logger LOG = LogManager.getLogger(OkHttpSearchServiceImpl.class);
    private OkHttpClient clientFactory;
    private ObjectMapper mapper;
    private CustomRequestRepository requestRepository;
    private UserService userService;
    private UriMapper uriMapper;

    @Autowired
    public OkHttpSearchServiceImpl(OkHttpClient clientFactory, ObjectMapper mapper,
                                   CustomRequestRepository requestRepository,
                                   UserService userService) {
        this.clientFactory = clientFactory;
        this.mapper = mapper;
        this.requestRepository = requestRepository;
        this.userService = userService;
    }

    @Autowired
    public void setUriMapper(UriMapper uriMapper) {
        this.uriMapper = uriMapper;
    }

    @Autowired
    public void setClientFactory(OkHttpClient clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public ResponseMapper<Integer, List<IdSearchResponseSlim>> searchList(CustomRequest jsonRequest) throws IOException {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userService.getUser(principal.getUsername()).getId();
        jsonRequest.setUserId(userId);
        jsonRequest.setTimeCreated(System.currentTimeMillis());
        Integer persistentId = requestRepository.save(jsonRequest).getId();
        List<IdSearchResponseSlim> list = getIdSearchResponseSlims(jsonRequest);

        ResponseMapper<Integer, List<IdSearchResponseSlim>> result = new ResponseMapper<>();
        result.setKey(persistentId);
        result.setValue(list);
        LOG.debug(String.format("searchList: count = %s", result.getValue().size()));

        return result;
    }

    @NotNull
    @Override
    public List<IdSearchResponseSlim> getIdSearchResponseSlims(CustomRequest jsonRequest) throws IOException {
        ApiSearchResponse response = mapper.readValue(
                Objects.requireNonNull(getResponse(uriMapper.getSearchUrlPattern(jsonRequest)).body())
                .bytes(), new TypeReference<>() {
        });

        List<IdSearchResponseSlim> list = new ArrayList<>();
        for (String id : response.getResponseResult().getSearchResult().getIds()) {
            final ResponseBody body = getResponse(uriMapper.getIdInfoUri(id))
                    .body();
            list.add(mapper.readValue(Objects.requireNonNull(body).bytes(), new TypeReference<>() {}));
        }
        return list;
    }

    @Override
    public IdSearchResponse searchById(String autoId) throws IOException {
        return mapper.readValue(Objects.requireNonNull(getResponse(uriMapper.getIdInfoUri(autoId)).body())
                .bytes(), new TypeReference<>() {});
    }

    @NotNull
    private Response getResponse(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Response response = clientFactory.newCall(request).execute();
        LOG.debug(String.format("getResponse code=%s,  url: %s", response.code(), url));
        return response;
    }

}
