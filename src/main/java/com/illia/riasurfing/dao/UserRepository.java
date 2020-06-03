package com.illia.riasurfing.dao;

import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    List<User> findByUserStatus(UserStatus userStatus);

}
