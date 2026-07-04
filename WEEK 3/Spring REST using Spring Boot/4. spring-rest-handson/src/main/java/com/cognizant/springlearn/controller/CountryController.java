package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final List<Country> countries = new ArrayList<>();

    public CountryController() {
        countries.add(new Country("US", "United States"));
        countries.add(new Country("IN", "India"));
        countries.add(new Country("UK", "United Kingdom"));
    }

    // GET all countries
    @GetMapping
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        return countries;
    }

    // GET a specific country
    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start");
        return findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Country not found with code " + code));
    }

    // Create a country based on data in payload
    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        countries.add(country);
        LOGGER.info("Country added: {}", country.getCode());
        return country;
    }

    // Update a country based on data in payload
    @PutMapping
    public Country updateCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        Country existing = findByCode(country.getCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Country not found with code " + country.getCode()));
        existing.setName(country.getName());
        return existing;
    }

    // Delete a specific country
    @DeleteMapping("/{code}")
    public void deleteCountry(@PathVariable String code) {
        LOGGER.info("Start");
        boolean removed = countries.removeIf(c -> c.getCode().equalsIgnoreCase(code));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found with code " + code);
        }
    }

    private Optional<Country> findByCode(String code) {
        return countries.stream().filter(c -> c.getCode().equalsIgnoreCase(code)).findFirst();
    }
}
