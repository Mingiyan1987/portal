package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Author;
import ru.basanov.repository.AuthorRepository;

@Service("userDetailService")
public class UserDetailsServiceBean implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Author author = findByUsername(username);
        if (author == null) throw new UsernameNotFoundException("Author not found");
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(author.getPassword());
        return builder.build();
    }

    private Author findByUsername(String login) {
        if (login== null || login.isEmpty()) return null;
        return authorRepository.findByLogin(login);

    }
}
