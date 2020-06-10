package com.illia.riasurfing.entities.search.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.illia.riasurfing.entities.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "search_history_gearbox_id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GearBoxId extends BaseEntity {
    private int gearboxId;

    public GearBoxId() {
    }

    @Column(name = "gearbox_id")
    public int getGearboxId() {
        return gearboxId;
    }

    public void setGearboxId(int gearboxId) {
        this.gearboxId = gearboxId;
    }
}
