package com.example.springtesting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Exercise 7: custom query method - Spring Data derives the query
    // from the method name automatically.
    List<User> findByName(String name);
}
