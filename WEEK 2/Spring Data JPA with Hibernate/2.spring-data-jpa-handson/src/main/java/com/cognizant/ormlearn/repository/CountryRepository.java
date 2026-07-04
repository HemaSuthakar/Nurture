package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.ormlearn.model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

    // Search box: find all countries whose name contains the given text
    List<Country> findByNameContaining(String text);

    // Same as above, but sorted alphabetically by name
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Alphabet index: find all countries whose name starts with the given letter
    List<Country> findByNameStartingWith(String letter);
}
