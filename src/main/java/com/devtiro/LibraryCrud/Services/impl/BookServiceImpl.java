package com.devtiro.LibraryCrud.Services.impl;

import com.devtiro.LibraryCrud.Repository.BookRepository;
import com.devtiro.LibraryCrud.Services.BookService;
import com.devtiro.LibraryCrud.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createOrUpdateBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findByTitleBetween("A", "M");
    }

    @Override
    public Optional<BookEntity> getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean checkItExists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity patchBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.findById(isbn).map(books -> {
            Optional.ofNullable(book.getTitle()).ifPresent(books::setTitle);
            Optional.ofNullable(book.getAuthorid()).ifPresent(books::setAuthorid);
            Optional.ofNullable(book.getImage()).ifPresent(books::setImage);
            return bookRepository.save(books);
        }).orElseThrow(() -> new RuntimeException("Book Not Found!"));
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
