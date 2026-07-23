package com.cognizant.springlearn.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @GetMapping("/countries")
    public List<Country> getCountries() {
        LOGGER.info("Start");
        List<Country> countries = Arrays.asList(
                new Country("US", "United States"),
                new Country("DE", "Germany"),
                new Country("IN", "India"),
                new Country("JP", "Japan"));
        LOGGER.info("End");
        return countries;
    }

}
