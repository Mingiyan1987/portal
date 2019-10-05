package ru.basanov.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.basanov.model.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    public Role findByName(String defaultRoleName);
}
