package com.illia.riasurfing.helpers;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.entities.search.request.CustomRequest;

import java.util.List;

public class UserHelper {
    public static final int F_USER_ID = 1;
    public static final String F_USERF_F_NAME = "Morgan";
    public static final String F_USER_L_NAME = "Freeman";
    public static final String F_USER_NICKNAME = "morgan";
    public static final int F_USER_AGE = 40;
    public static final String F_USER_EMAIL = "morgan@mail.com";
    public static final String F_USER_PASSWORD = "morgan";


    public static User createUserSimple(String fName, String nickname, int age, String email, String password) {
        User user = new User();
        user.setFirstName(fName);
        user.setNickname(nickname);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public static User createUser(int id, String fName, String lName, String nickname, int age, String email,
                                  String password, UserStatus status, UserRole role, List<CustomRequest> history) {
        User user = new User();
        user.setId(id);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setNickname(nickname);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserStatus(status);
        user.setUserRole(role);
        user.setSearchHistory(history);
        return user;
    }
}
