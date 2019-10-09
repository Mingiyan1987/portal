package ru.basanov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Author extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "{validation.author.firstname.pattern}")
    @Size(min=2, max = 50, message = "{validation.author.firstnmae.size}")
    @Column(name = "firstname", unique = true)
    private String firstname;

    @Pattern(regexp = "[a-zA-z]+(['-][a-zA-z]+)*", message = "{validation.author.lastname.pattern}")
    @Size(min = 2, max = 50, message = "{validation.author.lastname.size}")
    @Column(name = "lastname", unique = true)
    private String lastname;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-ZA-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "{validation.author.email.pattern}")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "{validation.author.login.pattern}")
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private byte tinyint;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Article> articles;

    @Override
    public <T> T getEntity(TypedQuery<T> query) {
        return super.getEntity(query);
    }

}
