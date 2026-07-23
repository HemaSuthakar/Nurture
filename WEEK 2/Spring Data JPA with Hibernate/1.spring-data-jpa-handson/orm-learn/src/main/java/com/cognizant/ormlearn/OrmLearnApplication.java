package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;
	private static EmployeeService employeeService;

	public static void main(String[] args) {
		LOGGER.info("Inside main");

		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);
		employeeService = context.getBean(EmployeeService.class);

		// Hands on 1 - list all countries
		testGetAllCountries();

		// Hands on 6 - find a country based on country code
		testFindCountryByCode();

		// Hands on 7 - add a new country
		testAddCountry();

		// Hands on 8 - update a country based on code
		testUpdateCountry();

		// Hands on 9 - delete a country based on code
		testDeleteCountry();

		// Objectives - find countries matching a partial name
		testFindCountriesByPartialName();

		// Hands on 4 - Spring Data JPA addEmployee(), compare against
		// EmployeeXmlDemo / EmployeeAnnotationDemo in the sibling projects
		testAddEmployee();
	}

	private static void testAddEmployee() {
		LOGGER.info("Start - testAddEmployee");
		Employee employee = new Employee("Priya", "Nair", 60000);
		employeeService.addEmployee(employee);
		LOGGER.debug("Added employee with generated id={}", employee.getId());
		LOGGER.info("End - testAddEmployee");
	}

	private static void testGetAllCountries() {
		LOGGER.info("Start - testGetAllCountries");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End - testGetAllCountries");
	}

	private static void testFindCountryByCode() {
		LOGGER.info("Start - testFindCountryByCode");
		try {
			Country country = countryService.findCountryByCode("IN");
			LOGGER.debug("Country:{}", country);
		} catch (CountryNotFoundException e) {
			LOGGER.error("Country not found", e);
		}
		LOGGER.info("End - testFindCountryByCode");
	}

	private static void testAddCountry() {
		LOGGER.info("Start - testAddCountry");
		Country newCountry = new Country("ZZ", "Zedland");
		countryService.addCountry(newCountry);
		try {
			Country fetched = countryService.findCountryByCode("ZZ");
			LOGGER.debug("Added country fetched back:{}", fetched);
		} catch (CountryNotFoundException e) {
			LOGGER.error("Country not found after add", e);
		}
		LOGGER.info("End - testAddCountry");
	}

	private static void testUpdateCountry() {
		LOGGER.info("Start - testUpdateCountry");
		try {
			countryService.updateCountry("ZZ", "Zedland Updated");
			Country updated = countryService.findCountryByCode("ZZ");
			LOGGER.debug("Updated country:{}", updated);
		} catch (CountryNotFoundException e) {
			LOGGER.error("Country not found for update", e);
		}
		LOGGER.info("End - testUpdateCountry");
	}

	private static void testDeleteCountry() {
		LOGGER.info("Start - testDeleteCountry");
		countryService.deleteCountry("ZZ");
		try {
			countryService.findCountryByCode("ZZ");
			LOGGER.debug("Country ZZ still present - delete may have failed");
		} catch (CountryNotFoundException e) {
			LOGGER.debug("Country ZZ deleted successfully");
		}
		LOGGER.info("End - testDeleteCountry");
	}

	private static void testFindCountriesByPartialName() {
		LOGGER.info("Start - testFindCountriesByPartialName");
		List<Country> countries = countryService.findCountriesByPartialName("land");
		LOGGER.debug("countries matching 'land'={}", countries);
		LOGGER.info("End - testFindCountriesByPartialName");
	}
}
