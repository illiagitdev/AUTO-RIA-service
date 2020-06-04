package com.illia.riasurfing.service;

import com.illia.riasurfing.dao.UserRepository;
import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.exceptions.UserEmailExistsException;
import com.illia.riasurfing.exceptions.UserNicknameExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void create(User user) throws UserEmailExistsException {
        userExists(user);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRegisterTime(System.currentTimeMillis());
        user.setUserRole(UserRole.ROLE_USER);
        user.setUserStatus(UserStatus.NEW);
        LOG.debug(String.format("create: %s, %s, %s, %s, %s, %s, %s,",
                user.getFirstName(), user.getLastName(), user.getNickname(), user.getAge(),
                user.getEmail(), user.getUserRole(), user.getUserStatus()));
        userRepository.save(user);
    }

    @Override
    public List<User> getList() {
        final List<User> users = userRepository.findAll();
        LOG.debug(String.format("getList: size %s", users.size()));
        return users.stream().peek(user -> user.setPassword("*****")).collect(Collectors.toList());
    }

    @Override
    public List<User> getListNew() {
        final List<User> users = userRepository.findByUserStatus(UserStatus.NEW);
        LOG.debug(String.format("getListNew: size %s", users.size()));
        return users.stream().peek(user -> user.setPassword("*****")).collect(Collectors.toList());
    }

    @Override
    public List<User> getListActive() {
        final List<User> users = userRepository.findByUserStatus(UserStatus.ACTIVE);
        LOG.debug(String.format("getListActive: size %s", users.size()));
        return users.stream().peek(user -> user.setPassword("*****")).collect(Collectors.toList());
    }

    @Override
    public List<User> getListDisabled() {
        final List<User> users = userRepository.findByUserStatus(UserStatus.DISABLED);
        LOG.debug(String.format("getListDisabled: size %s", users.size()));
        return users.stream().peek(user -> user.setPassword("*****")).collect(Collectors.toList());
    }

    @Override
    public User getUser(String nickname) {
        LOG.info(String.format("getUser(nickname): %s", nickname));
        return userRepository.findByNickname(nickname).orElseThrow(() ->
                new RuntimeException(String.format("User with nickname '%s' not found.", nickname)));
    }

    @Override
    public User getUser(Integer id) {
        LOG.info(String.format("getUser(id): %s", id));
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("User with id '%s' not found.", id)));
    }

    @Override
    public void delete(Integer id) {
        LOG.debug(String.format("delete: id=%s", id));
        userRepository.deleteById(id);
    }


    @Override
    public void update(User user) {
        User percistentUser = userRepository.getOne(user.getId());
        percistentUser.setFirstName(user.getFirstName());
        percistentUser.setLastName(user.getLastName());
        percistentUser.setNickname(user.getNickname());
        percistentUser.setAge(user.getAge());
        percistentUser.setEmail(user.getEmail());
        percistentUser.setPassword(encoder.encode(user.getPassword()));
        LOG.debug(String.format("update: %s, %s, %s, %s, %s, %s, %s,",
                user.getFirstName(), user.getLastName(), user.getNickname(), user.getAge(),
                user.getEmail(), user.getUserRole(), user.getUserStatus()));
        userRepository.save(percistentUser);
    }

    @Override
    public User updateRole(Integer id, UserRole userRole) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found."));
        user.setUserRole(userRole);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateStatus(Integer id, UserStatus status) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found."));
        user.setUserStatus(status);
        userRepository.save(user);
        return user;
    }

    private void userExists(User user) throws UserEmailExistsException {
        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            LOG.error(String.format("userExists: email = %s", user.getEmail()));
            throw new UserNicknameExistsException(
                    String.format("User with such nickname = '%s' already exists.", user.getNickname()));
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            LOG.error(String.format("userExists: email = %s", user.getEmail()));
            throw new UserEmailExistsException(
                    String.format("User with such email = '%s' already exists.", user.getEmail()));
        }
    }
}
