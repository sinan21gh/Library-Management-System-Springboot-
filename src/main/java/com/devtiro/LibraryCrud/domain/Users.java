package com.devtiro.LibraryCrud.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {


    @Id
    private String username;

    private String password;

    private String email;

    private String imageUrl;

    /* Many-to-Many with books
    @ManyToMany
    @JoinTable(
            name = "user_favorite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_isbn")
    )
    private Set<BookEntity> favoriteBooks = new HashSet<>();*/

}
