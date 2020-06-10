package com.illia.riasurfing.entities.search.request;

import com.illia.riasurfing.entities.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "currencies")
public class Currency extends BaseEntity {
    private String name;

    public Currency() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
