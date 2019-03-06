package ru.basanov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.basanov.model.Category;

import java.awt.print.Pageable;


@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>{
}
