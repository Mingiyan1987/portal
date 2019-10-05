package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Author;
import ru.basanov.model.Role;
import ru.basanov.repository.AuthorRepository;
import ru.basanov.repository.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AuthorService {

    private static final String DEFAULT_ROLE_NAME="user";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    private void initUser(final String username, final String password, final byte tinyint) {
        final Author author = authorRepository.findByLogin(username);
        if (author != null) return;
        createUser(username, password, tinyint);
    }

    @Transactional
    public void createUser(String login, String password, byte tinyint) {
        if (login==null || login.isEmpty()) return;
        if (password==null || password.isEmpty()) return;
        final String passwordHash = passwordEncoder.encode(password);
        final Author author = new Author();
        author.setLogin(login);
        author.setPassword(passwordHash);
        author.setTinyint(tinyint);
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Author get(Long id) {
        return authorRepository.findOne(id);
    }


    public Author findByLogin(String login) {
        return authorRepository.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public Author getByLogin(String login) {
        Author author = authorRepository.findByLogin(login);
        return author;
    }

    @Transactional
    public void save(Author author) {
        Role role = roleRepository.findByName(DEFAULT_ROLE_NAME);
        author.setRole(role);
        authorRepository.save(author);
    }

    @Transactional
    public void remove(Author author) {
        authorRepository.delete(author);
    }
}
