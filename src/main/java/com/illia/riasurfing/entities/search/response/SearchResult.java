package com.illia.riasurfing.entities.search.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResult {
    private List<String> ids;
    //count available ids for current request
    private int count;
    //number  ids in page
    private int size;
    //position based on page
    private int from;
}
