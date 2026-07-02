package com.cognizant.ormlearn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {

    // Search countries containing text
    List<Country> findByNameContainingIgnoreCase(String text);

    // Search countries containing text and sort ascending
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String text);

    // Countries starting with alphabet
    List<Country> findByNameStartingWithIgnoreCase(String alphabet);
}
