package com.devtiro.LibraryCrud.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDto {
    private String isbn;

    private String title;

    private AuthorDto authorid;
}
