package com.devtiro.LibraryCrud.controllers;

import com.devtiro.LibraryCrud.Mappers.Mapper;
import com.devtiro.LibraryCrud.Services.BookService;
import com.devtiro.LibraryCrud.domain.BookEntity;
import com.devtiro.LibraryCrud.domain.Dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private BookService bookService;
    private Mapper<BookEntity, BookDto> mapper;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PutMapping("/book/{ibsd}")
    public ResponseEntity<BookDto> createBook(@PathVariable String ibsd, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = mapper.mapToEntity(bookDto);
        boolean itExists = bookService.checkItExists(ibsd);
        BookEntity savedBookEntity = bookService.createOrUpdateBook(ibsd, bookEntity);
        BookDto savedBookDto = mapper.mapToDto(savedBookEntity);

        if (itExists) {
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    @GetMapping(path = "/books")
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<BookEntity> bookEntities = bookService.findAll(pageable);
        return bookEntities.map(mapper::mapToDto);
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("isbn") String isbn){
        Optional<BookEntity> bookEntity = bookService.getBookByIsbn(isbn);
        return bookEntity.map(bookEntity1 -> {
            BookDto bookDto = mapper.mapToDto(bookEntity1);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        if(!bookService.checkItExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookEntity bookEntity = mapper.mapToEntity(bookDto);
        BookEntity book = bookService.patchBook(isbn, bookEntity);
        return new ResponseEntity<>(mapper.mapToDto(book), HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity deleteAuthor(@PathVariable String isbn){
        if(!bookService.checkItExists(isbn)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBookByIsbn(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
