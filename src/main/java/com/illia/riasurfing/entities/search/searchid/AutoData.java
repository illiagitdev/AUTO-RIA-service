package com.illia.riasurfing.entities.search.searchid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoData {
    private String description;
    private int bodyId;
    private int year;
    private int autoId;
    private String race;
    private int fuelId;
    private String fuelName;
    private int gearBoxId;
    private String gearboxName;
    private int driveId;
    private String driveName;
    private String mainCurrency;
    private int categoryId;
    private boolean isSold;
}
