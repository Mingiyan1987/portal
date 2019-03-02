package ru.basanov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appCategory")
@Getter
@Setter
@NoArgsConstructor
public class Category extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name_category")
    private String nameCategory;

    @OneToMany(mappedBy = "category")
    private List<Ad> ads;

    public Category(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public <T> T getEntity(TypedQuery<T> query) {
        return super.getEntity(query);
    }
}
