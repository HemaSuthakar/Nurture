package com.cognizant.springlearn.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    private final List<Country> countryList = new ArrayList<>();

    public CountryService() {
        loadCountries();
    }

    private void loadCountries() {
        logger.info("Start loadCountries()");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("country.xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList countryNodes = document.getElementsByTagName("country");
            for (int i = 0; i < countryNodes.getLength(); i++) {
                Element element = (Element) countryNodes.item(i);
                String code = element.getElementsByTagName("code").item(0).getTextContent();
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                countryList.add(new Country(code, name));
            }
        } catch (Exception e) {
            logger.error("Error loading country.xml", e);
            throw new IllegalStateException("Unable to load country.xml", e);
        }
        logger.info("End loadCountries() - loaded {} countries", countryList.size());
    }

    public Country getCountryIndia() {
        return getCountry("IN");
    }

    public List<Country> getAllCountries() {
        return countryList;
    }

    public Country getCountry(String code) {
        logger.info("Start getCountry() - code={}", code);
        Country result = countryList.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException(code));
        logger.info("End getCountry()");
        return result;
    }
}
