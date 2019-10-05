package ru.basanov.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.basanov.model.Author;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    public Author findByLogin(String login);

    @Query("select a FROM Author a WHERE a.id=:id")
    public Author findOne(Long id);
}
