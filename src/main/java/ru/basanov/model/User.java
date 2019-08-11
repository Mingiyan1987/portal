package ru.basanov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "appUser")
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String passwordHash;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public <T> T getEntity(TypedQuery<T> query) {
        return super.getEntity(query);
    }
}
