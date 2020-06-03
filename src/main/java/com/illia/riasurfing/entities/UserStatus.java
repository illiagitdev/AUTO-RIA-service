package com.illia.riasurfing.entities;

import java.util.Arrays;
import java.util.Optional;

public enum  UserStatus {
    NEW("NEW"),
    ACTIVE("ACTIVE"),
    DISABLED("DISABLED");

    private String userStatus;

    UserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public static Optional<UserStatus> getUserStatusValue(String value) {
        return Arrays.stream(UserStatus.values())
                .filter(enumValue -> enumValue.getUserStatus().equals(value))
                .findAny();
    }
}
