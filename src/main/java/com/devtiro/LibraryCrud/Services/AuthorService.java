package com.devtiro.LibraryCrud.Services;

import com.devtiro.LibraryCrud.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    AuthorEntity createOrUpdateAuthor(AuthorEntity AuthorEntity);
    List<AuthorEntity> getAllAuthors();
    Optional<AuthorEntity> getSingleAuthorByid(Long id);
    boolean checkItExists(Long id);
    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);
    void deleteAuthorByid(Long id);
}
