package ru.basanov.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.basanov.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByLogin(String login);
}
