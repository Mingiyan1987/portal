package ru.basanov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Author;
import ru.basanov.model.Role;
import ru.basanov.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceBean implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Author author = findByUsername(username);
        System.out.println(author.getLogin());
        if (author == null) throw new UsernameNotFoundException("Author not found");
        System.out.println(author.getPassword());
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        System.out.println("автор");
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        System.out.println("2 - метка");
        builder.password(author.getPassword());
        List<String> list = new ArrayList<String>();
        builder.roles(author.getRoles().toArray(new String[list.size()]));
        System.out.println("Авторизация");
        return builder.build();
    }

    private Author findByUsername(String login) {
        if (login== null || login.isEmpty()) return null;
        return authorRepository.findByLogin(login);
    }
}
