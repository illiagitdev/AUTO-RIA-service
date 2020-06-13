package com.illia.riasurfing.entities.search.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.illia.riasurfing.entities.BaseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Table(name = "search_history")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomRequest extends BaseEntity {
    private Integer userId;
    private Integer categoryId;
    private List<AutoData> autoData;
    private Integer currency;
    private Integer priceOt;
    private Integer priceDo;
    private List<LocationIds> locationIds;
    private Integer colorId;

    private List<GearBoxId> gearboxIds;
    private List<FuelTypeId> fuelTypeIds;
    private List<DriveTypeId> driveTypesIds;

    private int top;    //criteria when post was added
    private int countpage;
    private long timeCreated;
    private boolean subscription;

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    @JoinColumn(name = "search_history_id")
    public List<AutoData> getAutoData() {
        return autoData;
    }

    public void setAutoData(List<AutoData> autoData) {
        this.autoData = autoData;
    }

    @Column(name = "currency")
    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    @Column(name = "price_ot")
    public Integer getPriceOt() {
        return priceOt;
    }

    public void setPriceOt(Integer priceOt) {
        this.priceOt = priceOt;
    }

    @Column(name = "price_do")
    public Integer getPriceDo() {
        return priceDo;
    }

    public void setPriceDo(Integer priceDo) {
        this.priceDo = priceDo;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_history_id")
    public List<LocationIds> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<LocationIds> locationIds) {
        this.locationIds = locationIds;
    }

    @Column(name = "color_id")
    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_history_id")
    public List<GearBoxId> getGearboxIds() {
        return gearboxIds;
    }

    public void setGearboxIds(List<GearBoxId> gearboxIds) {
        this.gearboxIds = gearboxIds;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_history_id")
    public List<FuelTypeId> getFuelTypeIds() {
        return fuelTypeIds;
    }

    public void setFuelTypeIds(List<FuelTypeId> fuelTypeIds) {
        this.fuelTypeIds = fuelTypeIds;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_history_id")
    public List<DriveTypeId> getDriveTypesIds() {
        return driveTypesIds;
    }

    public void setDriveTypesIds(List<DriveTypeId> driveTypesIds) {
        this.driveTypesIds = driveTypesIds;
    }

    @Column(name = "top")
    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Column(name = "countpage")
    public int getCountpage() {
        return countpage;
    }

    public void setCountpage(int countpage) {
        this.countpage = countpage;
    }

    @Column(name = "time_created")
    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Column(name = "subscription")
    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }
}
