package com.illia.riasurfing.helpers;

import com.illia.riasurfing.entities.search.request.*;

import java.util.List;

public class MethodHelper {
    public static final int CATEGORY_INDEX = 1;
    public static final int STATE_INDEX = 10;
    public static final int BRAND_INDEX = 9;

    public static List<Category> categoryList2() {
        Category item1 = new Category();
        item1.setName("Легковые");
        item1.setValue(1);
        Category item2 = new Category();
        item2.setName("Мото");
        item2.setValue(2);
        return List.of(item1, item2);
    }

    public static List<BodyStyle> bodyStyleList2() {
        BodyStyle item1 = new BodyStyle();
        item1.setValue(3);
        item1.setName("Седан");
        BodyStyle item2 = new BodyStyle();
        item2.setValue(8);
        item2.setName("Минивэн");
        return List.of(item1, item2);
    }

    public static List<State> statesList2() {
        State item1 = new State();
        item1.setValue(10);
        item1.setName("Киевская");
        State item2 = new State();
        item2.setValue(1);
        item2.setName("Винницкая");
        return List.of(item1, item2);
    }

    public static List<City> stateCitiesList2() {
        City item1 = new City();
        item1.setValue(10);
        item1.setName("Киев");
        City item2 = new City();
        item2.setValue(209);
        item2.setName("Барышевка");
        return List.of(item1, item2);
    }

    public static List<AutoColor> colorsList2() {
        AutoColor item1 = new AutoColor();
        item1.setValue(1);
        item1.setName("Бежевый");
        AutoColor item2 = new AutoColor();
        item2.setValue(2);
        item2.setName("Черный");
        return List.of(item1, item2);
    }

    public static List<AutoBrand> autoBrandList2() {
        AutoBrand item1 = new AutoBrand();
        item1.setValue(9);
        item1.setName("BMW");
        AutoBrand item2 = new AutoBrand();
        item2.setValue(22);
        item2.setName("Ferrari");
        return List.of(item1, item2);
    }

    public static List<Currency> currencyList2() {
        Currency item1 = new Currency();
        item1.setId(1);
        item1.setName("USD");
        Currency item2 = new Currency();
        item2.setId(2);
        item2.setName("EUR");
        return List.of(item1, item2);
    }

    public static List<TicketSubmission> ticketSubmissionList2() {
        TicketSubmission item1 = new TicketSubmission();
        item1.setValue(0);
        item1.setName("ALL_TIME");
        TicketSubmission item2 = new TicketSubmission();
        item2.setValue(1);
        item2.setName("HOUR");
        return List.of(item1, item2);
    }

    public static List<GearBox> gearboxesList2() {
        GearBox item1 = new GearBox();
        item1.setValue(2);
        item1.setName("Автомат");
        GearBox item2 = new GearBox();
        item2.setValue(4);
        item2.setName("Робот");
        return List.of(item1, item2);
    }

    public static List<FuelType> fuelTypesList2() {
        FuelType item1 = new FuelType();
        item1.setValue(1);
        item1.setName("Бензин");
        FuelType item2 = new FuelType();
        item2.setValue(2);
        item2.setName("Дизель");
        return List.of(item1, item2);
    }

    public static List<DriveType> driveTypeList2() {
        DriveType item1 = new DriveType();
        item1.setValue(1);
        item1.setName("Полный");
        DriveType item2 = new DriveType();
        item2.setValue(2);
        item2.setName("Передний");
        return List.of(item1, item2);
    }

    public static List<AutoModel> autoModelList2() {
        AutoModel item1 = new AutoModel();
        item1.setValue(54568);
        item1.setName("218");
        AutoModel item2 = new AutoModel();
        item2.setValue(3219);
        item2.setName("3 Series");
        return List.of(item1, item2);
    }
}
