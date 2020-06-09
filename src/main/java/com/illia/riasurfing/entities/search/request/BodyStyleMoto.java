package com.illia.riasurfing.entities.search.request;

import java.util.Arrays;
import java.util.Optional;

public enum BodyStyleMoto {
    OTHER("Другое", 56),
    KARTING("Картинг", 45),
    ATVS("Квадроциклы", 35),
    MINI_CROSS("Мини крос (Питбайк)", 33),
    MINI_SPORT("Мини спорт", 32),
    MOPED("Мопеды", 58),
    NAKED_BIKE("Мотоцикл Без обтекателей (Naked bike)", 15),
    OFF_ROAD_BIKE("Мотоцикл Внедорожный (Enduro)", 21),
    BIKE_CUSTOM("Мотоцикл Кастом", 30),
    BIKE_CLASSIC("Мотоцикл Классик", 14),
    BIKE_CROSS("Мотоцикл Кросс", 19),
    BIKE_CRUISE("Мотоцикл Круизер", 24),
    ALL_ROUND_BIKE("Мотоцикл Многоцелевой (All-round)", 25),
    SIDECAR_BIKE("Мотоцикл с коляской", 29),
    BIKE_SPORT_TOURISM("Мотоцикл Спорт-туризм", 17),
    BIKE_SUPERMOTO("Мотоцикл Супермото (Motard)", 22),
    BIKE_TRIAL("Мотоцикл Триал", 20),
    BIKE_TOURISM("Мотоцикл Туризм", 16),
    BIKE("Мотоциклы", 13),
    SCOOTER("Скутер / Мотороллер", 11),
    SNOW_BIKE("Снегоход", 46),
    SPORT_BIKE("Спортбайк", 18);

    private final String name;
    private final int value;

    BodyStyleMoto(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static Optional<BodyStyleMoto> getBodyStyleMotoValue(String value) {
        return Arrays.stream(BodyStyleMoto.values())
                .filter(enumValue -> enumValue.getName().equals(value))
                .findAny();
    }
}
