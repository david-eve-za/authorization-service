package com.glez.authorization_service.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private final IUsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        var user = usersService.getUserByUsername(username);

        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = user.getBody().getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> log.info("Role: {}", authority.getAuthority()))
                .collect(Collectors.toList());

        log.info("User: {} authenticated", user.getBody().getUsername());

        return new User(user.getBody().getUsername(), user.getBody().getPassword(),
                user.getBody().getEnabled(), true, true, true, authorities);
    }
}
