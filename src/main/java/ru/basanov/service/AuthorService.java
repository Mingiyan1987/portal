package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Author;
import ru.basanov.model.Role;
import ru.basanov.repository.AuthorRepository;
import ru.basanov.repository.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    @Transactional
    public void createUser(String login, String password) {
        if (login==null || login.isEmpty()) return;
        if (password==null || password.isEmpty()) return;
        final String passwordHash = passwordEncoder.encode(password);
        final Author author = new Author();
        final byte tinyint = 1;
        author.setPassword(passwordHash);
        author.setLogin(login);
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
        List<Role> role = (List<Role>) roleRepository.findByName(DEFAULT_ROLE_NAME);
        author.setRoles(role);
        authorRepository.save(author);
    }

    @Transactional
    public void remove(Author author) {
        authorRepository.delete(author);
    }
}
