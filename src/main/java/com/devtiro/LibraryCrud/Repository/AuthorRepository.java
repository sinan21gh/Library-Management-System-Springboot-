package com.devtiro.LibraryCrud.Repository;


import com.devtiro.LibraryCrud.domain.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
    @Query("select a from AuthorEntity a where a.age > ?1")
    List<AuthorEntity> AgemaybeGreaterThan(Integer ageIsGreaterThan);
}
