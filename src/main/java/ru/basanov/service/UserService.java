package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Role;
import ru.basanov.model.RoleType;
import ru.basanov.model.User;
import ru.basanov.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        initUser("admin", "admin", RoleType.ADMINISTRATOR);
        initUser("test", "test", RoleType.USER);
    }

    private void initUser(final String login, final String password, final RoleType roleType) {
        final User user = userRepository.findByLogin(login);
        if (user != null) return;
        createUser(login, password, roleType);
    }

    @Transactional
    public void createUser(String login, String password, RoleType roleType) {
        if (login==null || login.isEmpty()) return;
        if (password==null || password.isEmpty()) return;
        final String passwordHash = passwordEncoder.encode(password);
        final User user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        final Role role = new Role();
        role.setUser(user);
        role.setRoleType(roleType);
        user.setRoles(Collections.singletonList(role));
        userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
