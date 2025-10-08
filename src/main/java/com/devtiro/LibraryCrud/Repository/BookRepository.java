package com.devtiro.LibraryCrud.Repository;

import com.devtiro.LibraryCrud.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,String>,
        PagingAndSortingRepository<BookEntity,String> {
    List<BookEntity> findByTitleBetween(String titleAfter, String titleBefore);
}
