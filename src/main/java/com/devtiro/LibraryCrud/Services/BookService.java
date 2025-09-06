package com.devtiro.LibraryCrud.Services;

import com.devtiro.LibraryCrud.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createOrUpdateBook(String isbn, BookEntity book);
    List<BookEntity> getAllBooks();
    Optional<BookEntity> getBookByIsbn(String isbn);
    boolean checkItExists(String isbn);
    BookEntity patchBook(String isbn, BookEntity book);
    void  deleteBookByIsbn(String isbn);
    Page<BookEntity> findAll(Pageable pageable);
}
