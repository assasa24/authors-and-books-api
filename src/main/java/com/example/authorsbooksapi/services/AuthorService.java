package com.example.authorsbooksapi.services;

import com.example.authorsbooksapi.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity save(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    boolean isExist(Long id);

    AuthorEntity partialUpdate(long id, AuthorEntity authorEntity);

    void delete(Long id);
}
