package com.illia.riasurfing.entities.search.searchid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoData {
    private String seoLinkM;
    private String seoLinkSX;
    private String seoLinkB;
    private String seoLinkF;
}
