package com.illia.riasurfing.service;

import com.illia.riasurfing.common.UserServiceConfig;
import com.illia.riasurfing.dao.CustomRequestRepository;
import com.illia.riasurfing.dao.UserRepository;
import com.illia.riasurfing.entities.User;
import com.illia.riasurfing.entities.UserRole;
import com.illia.riasurfing.entities.UserStatus;
import com.illia.riasurfing.exceptions.UserNicknameExistsException;
import com.illia.riasurfing.exceptions.UserNotExistsException;
import com.illia.riasurfing.helpers.UserHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceConfig.class})
public class UserServiceTest {

    @MockBean
    public UserRepository userRepository;

    @MockBean
    public CustomRequestRepository requestRepository;

    @Autowired
    public UserService userService;

    @Test
    public void testGetUserByIdReturnUser() {
        //given
        when(userRepository.findById(UserHelper.F_USER_ID)).thenReturn(Optional.of(UserHelper.setFirstUser()));
        //when
        final User user = userService.getUser(UserHelper.F_USER_ID);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getNickname()).isEqualTo(UserHelper.F_USER_NICKNAME);
    }

    @Test(expected = UserNotExistsException.class)
    public void testGetUserByIdThrowException() {
        //given
        when(userRepository.findById(UserHelper.F_USER_ID)).thenReturn(Optional.empty());
        //when
        userService.getUser(UserHelper.F_USER_ID);
    }

    @Test
    public void testGetUserByNicknameReturnUser() {
        //given
        when(userRepository.findByNickname(UserHelper.F_USER_NICKNAME)).thenReturn(Optional.of(UserHelper.setFirstUser()));
        //when
        final User user = userService.getUser(UserHelper.F_USER_NICKNAME);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getNickname()).isEqualTo(UserHelper.F_USER_NICKNAME);
    }

    @Test(expected = UserNicknameExistsException.class)
    public void testGetUserByNicknameThrowException() {
        //given
        when(userRepository.findByNickname(UserHelper.F_USER_NICKNAME)).thenReturn(Optional.empty());
        //when
        userService.getUser(UserHelper.F_USER_NICKNAME);
    }

    @Test
    public void testDeleteUser() {
        //given
        //when
        userService.delete(UserHelper.F_USER_ID);
        //then
        verify(userRepository, times(1)).deleteById(eq(UserHelper.F_USER_ID));
    }

    @Test
    public void testUpdateRoleShouldReturnUser() {
        //given
        final User user = UserHelper.setFirstUser();
        when(userRepository.findById(UserHelper.F_USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        //when
        final User updateRole = userService.updateRole(UserHelper.F_USER_ID, UserRole.ROLE_ADMIN);
        //then
        assertThat(updateRole).isNotNull();
        assertThat(updateRole.getUserRole()).isEqualTo(UserRole.ROLE_ADMIN);
    }

    @Test(expected = UserNotExistsException.class)
    public void testUpdateRoleShouldThrowException() {
        //given
        final User user = UserHelper.setFirstUser();
        when(userRepository.findById(UserHelper.F_USER_ID)).thenReturn(Optional.of(user));
        //when
        userService.updateRole(UserHelper.INCORRECT_ID, UserRole.ROLE_ADMIN);
    }

    @Test
    public void testUpdateStatusShouldReturnUser() {
        //given
        final User user = UserHelper.setFirstUser();
        when(userRepository.findById(UserHelper.F_USER_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        //when
        final User updateStatus = userService.updateStatus(UserHelper.F_USER_ID, UserStatus.ACTIVE);
        //then
        assertThat(updateStatus).isNotNull();
        assertThat(updateStatus.getUserStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test(expected = UserNotExistsException.class)
    public void testUpdateStatusShouldThrowException() {
        //given
        final User user = UserHelper.setFirstUser();
        when(userRepository.findById(UserHelper.F_USER_ID)).thenReturn(Optional.of(user));
        //when
        userService.updateStatus(UserHelper.INCORRECT_ID, UserStatus.ACTIVE);
    }

}
