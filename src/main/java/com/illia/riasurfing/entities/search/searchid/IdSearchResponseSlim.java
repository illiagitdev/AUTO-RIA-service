package com.illia.riasurfing.entities.search.searchid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "Search")
public class IdSearchResponseSlim {
    private int userId;
    private String locationCityName;
    @JsonProperty("USD")
    private int usd;
    private String markName;
    private String modelName;
    private PhotoData photoData;
    private String linkToView;
    private String title;
}
