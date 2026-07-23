package com.cognizant.ormlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.cognizant.ormlearn.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;

// Runs against the H2 profile so the test suite needs no MySQL server.
@SpringBootTest
@ActiveProfiles("h2")
class OrmLearnApplicationTests {

	@Autowired
	private CountryService countryService;

	@Test
	void contextLoads() {
		assertNotNull(countryService);
	}

	@Test
	void getAllCountriesReturnsSeededData() {
		assertNotNull(countryService.getAllCountries());
	}
}
