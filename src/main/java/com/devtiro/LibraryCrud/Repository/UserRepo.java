package com.devtiro.LibraryCrud.Repository;

import com.devtiro.LibraryCrud.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
