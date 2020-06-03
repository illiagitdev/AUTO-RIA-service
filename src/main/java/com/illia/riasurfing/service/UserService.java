package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.exceptions.UserEmailExistsException;

import java.util.List;

public interface UserService {

    void create(User user) throws UserEmailExistsException;
    User getUser(String nickname);
    User getUser(Integer id);
    void delete(Integer id);
    void update(User user);
    User updateRole(Integer id, UserRole userRole);
    User updateStatus(Integer id, UserStatus status);
    List<User> getList();
    List<User> getListNew();
    List<User> getListActive();
    List<User> getListDisabled();

}
