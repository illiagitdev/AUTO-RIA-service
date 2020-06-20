package com.illia.riasurfing.entities.search.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
@ApiModel(value = "Model")
public class MappingEntity {
    private String name;
    private int value;
}
