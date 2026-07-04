package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        logger.info("CountryController instance created");
    }

    @RequestMapping("/country")
    public Country getCountryIndia() {
        logger.info("Start getCountryIndia()");
        Country country = countryService.getCountryIndia();
        logger.info("End getCountryIndia()");
        return country;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        logger.info("Start getAllCountries()");
        List<Country> countries = countryService.getAllCountries();
        logger.info("End getAllCountries()");
        return countries;
    }

    @GetMapping({ "/countries/{code}", "/country/{code}" })
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        logger.info("Start getCountry() - code={}", code);
        Country country = countryService.getCountry(code);
        logger.info("End getCountry()");
        return country;
    }
}
