package com.example.ormlearn.repository;

import com.example.ormlearn.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Hands-on 3: Fetch quiz attempt details using HQL
//
// Join order follows the hands-on instructions:
//   user -> attempt -> attempt_question -> question -> attempt_option -> option
// "fetch" is added on every one-to-many / many-to-many hop so the whole
// object graph is populated in a single SQL statement.
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    @Query(value = "SELECT distinct a FROM Attempt a " +
            "join fetch a.user u " +
            "join fetch a.attemptQuestions aq " +
            "join fetch aq.question q " +
            "join fetch aq.attemptOptions ao " +
            "join fetch ao.option o " +
            "WHERE u.id = :userId and a.id = :attemptId")
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
