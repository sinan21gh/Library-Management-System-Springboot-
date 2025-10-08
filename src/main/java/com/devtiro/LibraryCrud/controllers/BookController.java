package com.devtiro.LibraryCrud.controllers;

import com.devtiro.LibraryCrud.Mappers.Mapper;
import com.devtiro.LibraryCrud.Services.AuthorService;
import com.devtiro.LibraryCrud.Services.BookService;
import com.devtiro.LibraryCrud.Services.JWTService;
import com.devtiro.LibraryCrud.domain.AuthorEntity;
import com.devtiro.LibraryCrud.domain.BookEntity;
import com.devtiro.LibraryCrud.domain.Dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private BookService bookService;
    private Mapper<BookEntity, BookDto> mapper;
    private JWTService jwtService;
    private AuthorService authorService;

    public BookController(BookService bookService, Mapper<BookEntity, BookDto> mapper, AuthorService authorService, JWTService jwtService) {
        this.bookService = bookService;
        this.mapper = mapper;
        this.authorService = authorService;
        this.jwtService = jwtService;
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/book")
    public ResponseEntity<BookDto> createBook(
            @RequestParam("isbn") String isbn,
            @RequestParam("title") String title,
            @RequestParam("authorid") Long authorid,
            @RequestParam("image") MultipartFile imageFile) {

        try {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setIsbn(isbn);
            bookEntity.setTitle(title);

            if (authorService.checkItExists(authorid)) {
                AuthorEntity author = authorService.getAuthorById(authorid);
                author.setAuthorid(authorid);
                bookEntity.setAuthorid(author);

                bookEntity.setImage(imageFile.getBytes());

                BookEntity saved = bookService.createOrUpdateBook(isbn, bookEntity);
                BookDto savedDto = mapper.mapToDto(saved);

                return ResponseEntity.ok(savedDto);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/book/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        BookEntity bookEntity = mapper.mapToEntity(bookDto);

        AuthorEntity author = bookEntity.getAuthorid();
        if (!authorService.getAuthorById(bookEntity.getAuthorid().getAuthorid()).equals(author)) {
            return ResponseEntity.notFound().build();
        }

        boolean itExists = bookService.checkItExists(isbn);
        BookEntity savedBookEntity = bookService.createOrUpdateBook(isbn, bookEntity);
        BookDto savedBookDto = mapper.mapToDto(savedBookEntity);

        if (itExists) {
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

//

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(path = "/books")
    public List<BookDto> getAllBooks() {
        List<BookEntity> bookEntities = bookService.getAllBooks();
        return bookEntities.stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    /*@CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(path = "/books")
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<BookEntity> bookEntities = bookService.findAll(pageable);
        return bookEntities.map(mapper::mapToDto);
    }*/

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable("isbn") String isbn, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<BookEntity> bookEntity = bookService.getBookByIsbn(isbn);

        return bookEntity.map(bookEntity1 -> {
            BookDto bookDto = mapper.mapToDto(bookEntity1);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> updateBook(@PathVariable String isbn, @RequestBody BookDto bookDto) {
        if(!bookService.checkItExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookEntity bookEntity = mapper.mapToEntity(bookDto);
        BookEntity book = bookService.patchBook(isbn, bookEntity);
        return new ResponseEntity<>(mapper.mapToDto(book), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/books/{isbn}")
    public ResponseEntity deleteAuthor(@PathVariable String isbn){
        if(!bookService.checkItExists(isbn)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBookByIsbn(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
