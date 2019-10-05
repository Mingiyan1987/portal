package ru.basanov.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appAd")
@Getter
@Setter
@NoArgsConstructor
public class Article extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; // связь с категорией

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // связь с компанией

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Author author; // связь с пользователем

    @Column(name = "title")
    private String title;

    @Column(name = "published_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date publishedDate;

    @Column(name = "content")
    private String content;

    @Override
    public <T> T getEntity(TypedQuery<T> query) {
        return super.getEntity(query);
    }
}
