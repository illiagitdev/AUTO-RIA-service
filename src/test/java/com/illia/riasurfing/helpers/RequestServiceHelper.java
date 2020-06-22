package com.illia.riasurfing.helpers;

import com.illia.riasurfing.entities.search.request.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RequestServiceHelper {
    public static List<Integer> getSubscriptionsList2() {
        return List.of(1, 4);
    }

    public static Page<CustomRequest> getSubscriptionsForUserList2(int userId, Pageable of) {
        CustomRequest item1 = createCustomRequest(1, userId);
        CustomRequest item2 = createCustomRequest(2, userId);
        final List<CustomRequest> items = List.of(item1, item2);
        long total = of.isPaged() ? of.getPageSize() : items.size();
        return new PageImpl<>(items, of, total);
    }

    private static CustomRequest createCustomRequest(int id, int userId) {
        final int DEFAULT = 1;
        AutoData autoData = new AutoData();
        autoData.setId(id);
        autoData.setBodyId(DEFAULT);
        autoData.setMarkaId(DEFAULT);
        autoData.setModelId(DEFAULT);
        autoData.setYearsFrom(2000);
        autoData.setYearsTo(2020);

        LocationIds locationIds = new LocationIds();
        locationIds.setId(DEFAULT);
        locationIds.setState(DEFAULT);
        locationIds.setCity(DEFAULT);

        GearBoxId gearBoxId = new GearBoxId();
        gearBoxId.setId(DEFAULT);
        gearBoxId.setGearboxId(DEFAULT);

        FuelTypeId fuelTypeId = new FuelTypeId();
        fuelTypeId.setId(DEFAULT);
        fuelTypeId.setFuelType(DEFAULT);

        DriveTypeId driveTypeId = new DriveTypeId();
        driveTypeId.setId(DEFAULT);
        driveTypeId.setDriveTypeId(DEFAULT);

        CustomRequest item = new CustomRequest();
        item.setId(id);
        item.setUserId(userId);
        item.setCategoryId(DEFAULT);
        item.setAutoData(List.of(autoData));
        item.setCurrency(DEFAULT);
        item.setPriceOt(1000);
        item.setPriceDo(10000);
        item.setLocationIds(List.of(locationIds));
        item.setColorId(DEFAULT);
        item.setGearboxIds(List.of(gearBoxId));
        item.setFuelTypeIds(List.of(fuelTypeId));
        item.setDriveTypesIds(List.of(driveTypeId));
        item.setTop(DEFAULT);
        item.setColorId(DEFAULT);
        item.setTimeCreated(DEFAULT);
        item.setSubscription(true);
        return item;
    }
}
