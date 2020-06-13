package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.ResponseMapper;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponse;
import com.illia.riasurfing.entities.search.searchid.IdSearchResponseSlim;

import java.io.IOException;
import java.util.List;

public interface HttpClientService {

    ResponseMapper<Integer, List<IdSearchResponseSlim>> searchList(CustomRequest jsonRequest) throws IOException;

    IdSearchResponse searchById(String params) throws IOException;

}
