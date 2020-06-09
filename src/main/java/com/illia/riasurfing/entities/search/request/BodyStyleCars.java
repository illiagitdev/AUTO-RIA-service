package com.illia.riasurfing.entities.search.request;

import java.util.Arrays;
import java.util.Optional;

public enum BodyStyleCars {
    SEDAN("Седан", 3),
    CROSSOVER("Внедорожник / Кроссовер", 5),
    MINIVAN("Минивэн", 8),
    HATCHBACK("Хэтчбек", 4),
    UNIVERSAILS("Универсал", 2),
    COUPE("Купе", 6),
    CABRIOLET("Кабриолет", 7),
    PICKUP("Пикап", 9),
    LIMOUSINE("Лимузин", 252),
    OTHER("Другой", 28);

    private final String name;
    private final int value;

    BodyStyleCars(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static Optional<BodyStyleCars> getBodyStyleCarsValue(String value) {
        return Arrays.stream(BodyStyleCars.values())
                .filter(enumValue -> enumValue.getName().equals(value))
                .findAny();
    }
}
