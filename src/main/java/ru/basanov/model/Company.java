package ru.basanov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appCompany")
@Getter
@Setter
@NoArgsConstructor
public class Company extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Article> articles;

    public Company(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @Override
    public <T> T getEntity(TypedQuery<T> query) {
        return super.getEntity(query);
    }
}
