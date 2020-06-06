package com.illia.riasurfing.entities.search.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
//parsing response from provider API
public class ApiSearchResponse {
    @JsonProperty(value = "additional_params")
    private AdditionalParams additionalParams;
    @JsonProperty(value = "result")
    private ResponseResult responseResult;
}
