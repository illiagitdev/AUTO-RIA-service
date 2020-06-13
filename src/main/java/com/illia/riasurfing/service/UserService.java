package com.illia.riasurfing.service;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import com.illia.riasurfing.exceptions.UserEmailExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    void enableSubscription(Integer requestId);
    void disableSubscription(Integer requestId);
    Page<CustomRequest> getSearchHistoryPage(int userId, Pageable p);

}
