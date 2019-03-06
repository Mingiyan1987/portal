package ru.basanov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "appRole")
@Getter
@Setter
@NoArgsConstructor
public class Role extends AbstractEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType = RoleType.USER;

    public Role(User user, RoleType roleType) {
        this.user = user;
        this.roleType = roleType;
    }

    @Override
    public <T> T getEntity(TypedQuery<T> query) {
        return super.getEntity(query);
    }
}
