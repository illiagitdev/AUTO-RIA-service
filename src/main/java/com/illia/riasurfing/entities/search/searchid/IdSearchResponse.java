package com.illia.riasurfing.entities.search.searchid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdSearchResponse {
    private int userId;
    private String locationCityName;
    @JsonProperty("userPhoneData")
    private PhoneData phoneData;
    @JsonProperty("USD")
    private int usd;
    @JsonProperty("UAH")
    private int uah;
    @JsonProperty("EUR")
    private int eur;
    private AutoData autoData;
    private String markName;
    private int markId;
    private String modelName;
    private int modelId;
    private PhotoData photoData;
    private String linkToView;
    private String title;
    private StateData stateData;
}
