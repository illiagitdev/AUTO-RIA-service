package com.illia.riasurfing.entities.search.request;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
    CARS("Легковые", 1),
    MOTO("Мото", 2);

    private final String name;
    private final int value;

    Category(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static Optional<Category> getCategoryValue(String value) {
        return Arrays.stream(Category.values())
                .filter(enumValue -> enumValue.getName().equals(value))
                .findAny();
    }
}
