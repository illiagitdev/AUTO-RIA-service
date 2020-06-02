package com.illia.riasurfing.entities;

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
}
