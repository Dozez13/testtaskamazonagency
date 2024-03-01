package com.example.demo.domain.repository;

import com.example.demo.domain.document.User;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @ExistsQuery("{'email': :#{#email}}")
    boolean existUserByEmail(@Param("email") String email);
    @Query("{'email': :#{#email}}")
    User findUserByEmail(@Param("email") String email);
}
