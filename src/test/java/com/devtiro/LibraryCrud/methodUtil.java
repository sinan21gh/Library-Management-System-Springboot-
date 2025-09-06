package com.devtiro.LibraryCrud;

import com.devtiro.LibraryCrud.domain.AuthorEntity;
import com.devtiro.LibraryCrud.domain.BookEntity;

public class methodUtil {
    private methodUtil() {}
    public static AuthorEntity createAuthor1() {
        return AuthorEntity.builder()
                .authorid(1001L)
                .name("sinan")
                .age(18)
                .build();
    }
    public static AuthorEntity createAuthor2() {
        return AuthorEntity.builder()
                .authorid(1002L)
                .name("ali")
                .age(19)
                .build();
    }
    public static AuthorEntity createAuthor3() {
        return AuthorEntity.builder()
                .authorid(1003L)
                .name("alin")
                .age(20)
                .build();
    }
    public static BookEntity createBook1(final AuthorEntity authorEntity1) {
        return BookEntity.builder()
                .isbn("123-4556-677")
                .title("harry potter")
                .authorid(authorEntity1)
                .build();
    }
    public static BookEntity createBook2(final AuthorEntity authorEntity1) {
        return BookEntity.builder()
                .isbn("123-4556-678")
                .title("harry potter goblet of fire")
                .authorid(authorEntity1)
                .build();
    }
}
