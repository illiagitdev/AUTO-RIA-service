package com.illia.riasurfing.entities.search.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.illia.riasurfing.entities.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "search_history_car_data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoData extends BaseEntity {
    private Integer bodyId;
    private Integer markaId;
    private Integer modelId;
    private Integer yearsFrom;
    private Integer yearsTo;

    public AutoData() {
    }

    @Column(name = "body_id")
    public Integer getBodyId() {
        return bodyId;
    }

    public void setBodyId(Integer bodyId) {
        this.bodyId = bodyId;
    }

    @Column(name = "marka_id")
    public Integer getMarkaId() {
        return markaId;
    }

    public void setMarkaId(Integer markaId) {
        this.markaId = markaId;
    }

    @Column(name = "model_id")
    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    @Column(name = "s_years")
    public Integer getYearsFrom() {
        return yearsFrom;
    }

    public void setYearsFrom(Integer yearsFrom) {
        this.yearsFrom = yearsFrom;
    }

    @Column(name = "to_years")
    public Integer getYearsTo() {
        return yearsTo;
    }

    public void setYearsTo(Integer yearsTo) {
        this.yearsTo = yearsTo;
    }
}
