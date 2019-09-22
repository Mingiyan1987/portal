package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.User;
import ru.basanov.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    private void initUser(final String username, final String password, final byte tinyint) {
        final User user = userRepository.findByUsername(username);
        if (user != null) return;
        createUser(username, password, tinyint);
    }

    @Transactional
    public void createUser(String login, String password, byte tinyint) {
        if (login==null || login.isEmpty()) return;
        if (password==null || password.isEmpty()) return;
        final String passwordHash = passwordEncoder.encode(password);
        final User user = new User();
        user.setUsername(login);
        user.setPassword(passwordHash);
        user.setTinyint(tinyint);
        userRepository.save(user);
    }

    public User findByUserName(String login) {
        return userRepository.findByUsername(login);
    }
}
