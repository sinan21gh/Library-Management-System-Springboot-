package com.devtiro.LibraryCrud.domain.Dto;

import com.devtiro.LibraryCrud.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDto {
    private String isbn;

    private String title;

    private AuthorDto authorid;

    private String imageBase64;

//    private Set<Users> users = new HashSet<>();
}
