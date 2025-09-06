package com.devtiro.LibraryCrud.controllers;


import com.devtiro.LibraryCrud.Services.AuthorService;
import com.devtiro.LibraryCrud.Mappers.Mapper;
import com.devtiro.LibraryCrud.domain.AuthorEntity;
import com.devtiro.LibraryCrud.domain.Dto.AuthorDto;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Log
public class AuthorController {

    private AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDto> mapper;

    public AuthorController(AuthorService authors1,  Mapper<AuthorEntity, AuthorDto> mapper) {
        this.authorService = authors1;
        this.mapper = mapper;
    }

    @PostMapping(path = "/about")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto Authordto){
        AuthorEntity authorentity = mapper.mapToEntity(Authordto);
        AuthorEntity authorEntity1 = authorService.createOrUpdateAuthor(authorentity);
        AuthorDto authorsdto = mapper.mapToDto(authorEntity1);
        return new ResponseEntity<>(authorsdto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/about")
    public List<AuthorDto> getAllAuthors(){
        List<AuthorEntity> author = authorService.getAllAuthors();
        return author.stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping(path = "/about/{authorid}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long authorid){
        Optional<AuthorEntity> author = authorService.getSingleAuthorByid(authorid);
        return author.map(authorEntity -> {
            AuthorDto authorDto = mapper.mapToDto(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/about/{authorid}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long authorid, @RequestBody AuthorDto authorDto){
        if (!authorService.checkItExists(authorid)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorDto.setAuthorid(authorid);
        AuthorEntity authorDto1 = mapper.mapToEntity(authorDto);
        AuthorEntity author = authorService.createOrUpdateAuthor(authorDto1);
        return new ResponseEntity<>(mapper.mapToDto(author), HttpStatus.OK);
    }

    @PatchMapping("/about/{id}")
    public ResponseEntity<AuthorDto> patchAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto){
        if (!authorService.checkItExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = mapper.mapToEntity(authorDto);
        AuthorEntity authorEntity1 = authorService.partialUpdate(id, authorEntity);
        return new ResponseEntity<>(mapper.mapToDto(authorEntity1), HttpStatus.OK);
    }

    @DeleteMapping("/about/{id}")
    public ResponseEntity deleteAuthor(@PathVariable Long id){
        if(!authorService.checkItExists(id)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthorByid(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
