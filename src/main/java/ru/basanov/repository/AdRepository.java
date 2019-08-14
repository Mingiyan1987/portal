package ru.basanov.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ru.basanov.model.Ad;

@Repository
public interface AdRepository extends PagingAndSortingRepository<Ad, Long> {

    @Query("select a FROM Ad a WHERE a.category.id=:id")
    public Page<Ad> findByCategoryId(@Param("id") Long id, Pageable pageable);
}
