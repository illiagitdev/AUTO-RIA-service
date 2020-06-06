package com.illia.riasurfing.entities;

import com.illia.riasurfing.entities.search.request.CustomRequest;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Component
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private int age;
    private String nickname;
    private String email;
    private String password;
    private long registerTime;
    private UserRole userRole;
    private UserStatus userStatus;
    private List<Subscription> subscriptions;
    private List<CustomRequest> searchHistory;

    public User() {
    }

    @NotBlank(message = "first name shouldn't be empty")
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age")
    @Positive(message = "age should be above zero")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotBlank(message = "nickname shouldn't be empty")
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Email
    @NotBlank
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "password can't be empty")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "register_time")
    public long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(long registerTime) {
        this.registerTime = registerTime;
    }

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Transient
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Transient
    public List<CustomRequest> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(List<CustomRequest> searchHistory) {
        this.searchHistory = searchHistory;
    }
}
