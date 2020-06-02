package com.illia.riasurfing.security;

import com.illia.riasurfing.dao.UserRepository;
import com.illia.riasurfing.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with name %s not exists.", username)));
        return new UserPrincipal(user);
    }
}
