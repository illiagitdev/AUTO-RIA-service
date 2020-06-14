package com.illia.riasurfing.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.illia.riasurfing.entities.search.request.CustomRequest;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Component
@Table(name = "users")
@JsonIgnoreProperties(value = {"id", "registerTime", "userRole", "userStatus", "searchHistory"})
public class User extends BaseEntity {
    @ApiModelProperty(notes = "User first name")
    private String firstName;
    @ApiModelProperty(notes = "User last name")
    private String lastName;
    @ApiModelProperty(notes = "User first age")
    private int age;
    @ApiModelProperty(notes = "User nickname")
    private String nickname;
    @ApiModelProperty(notes = "User email")
    private String email;
    @ApiModelProperty(notes = "User password")
    private String password;
    @ApiModelProperty(notes = "Time user created", hidden = true)
    private long registerTime;
    @ApiModelProperty(notes = "User role, for admin")
    private UserRole userRole;
    @ApiModelProperty(notes = "User status, for admin")
    private UserStatus userStatus;
    @ApiModelProperty(notes = "User search history", hidden = true)
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

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    public List<CustomRequest> getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(List<CustomRequest> searchHistory) {
        this.searchHistory = searchHistory;
    }
}
