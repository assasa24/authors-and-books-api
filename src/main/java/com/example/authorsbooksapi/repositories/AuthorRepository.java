package com.example.authorsbooksapi.repositories;

import com.example.authorsbooksapi.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>{
    Iterable<AuthorEntity> ageLessThan(int age);

    Iterable<AuthorEntity> ageGreaterThan(int i);
}
