package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Role;
import ru.basanov.model.User;
import ru.basanov.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
public class UserDetailsServiceBean implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = findByLogin(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder.password(user.getPasswordHash());
        final List<Role> userRoles = user.getRoles();
        final List<String> roles = new ArrayList<>();
        for (Role role: userRoles) roles.add(role.toString());
        builder.roles(roles.toArray(new String[]{}));
        return builder.build();
    }

    private User findByLogin(String username) {
        if (username == null || username.isEmpty()) return null;
        return userRepository.findByLogin(username);

    }
}
