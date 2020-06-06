package com.illia.riasurfing.entities.search.searchid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateData {
    private String name;
    private String regionName;
    private int stateId;
    private int cityId;
}
