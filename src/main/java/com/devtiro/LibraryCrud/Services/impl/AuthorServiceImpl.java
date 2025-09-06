package com.devtiro.LibraryCrud.Services.impl;

import com.devtiro.LibraryCrud.Services.AuthorService;
import com.devtiro.LibraryCrud.Repository.AuthorRepository;
import com.devtiro.LibraryCrud.domain.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service

public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createOrUpdateAuthor(AuthorEntity AuthorEntity) {
        return authorRepository.save(AuthorEntity);
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return StreamSupport.stream(authorRepository.findAll()
                        .spliterator(), false)
                        .collect(Collectors.toList());

    }

    @Override
    public Optional<AuthorEntity> getSingleAuthorByid(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean checkItExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setAuthorid(id);
        return authorRepository.findById(id).map(authors -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(authors::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(authors::setAge);
            return authorRepository.save(authors);
        }).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public void deleteAuthorByid(Long id) {
        authorRepository.deleteById(id);
    }
}
