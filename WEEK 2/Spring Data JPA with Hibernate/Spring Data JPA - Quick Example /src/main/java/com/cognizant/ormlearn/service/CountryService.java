package com.cognizant.ormlearn.service;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
@Service
public class CountryService{
@Autowired private CountryRepository countryRepository;
@Transactional public List<Country> getAllCountries(){ return countryRepository.findAll(); }
@Transactional public Country findCountryByCode(String code) throws CountryNotFoundException{
Optional<Country> c=countryRepository.findById(code);
if(!c.isPresent()) throw new CountryNotFoundException("Country Not Found");
return c.get();
}
@Transactional public void addCountry(Country c){ countryRepository.save(c); }
@Transactional public void updateCountry(String code,String name) throws CountryNotFoundException{
Country c=findCountryByCode(code); c.setName(name); countryRepository.save(c);
}
@Transactional public void deleteCountry(String code){ countryRepository.deleteById(code); }
@Transactional public List<Country> findCountriesByPartialName(String name){
return countryRepository.findByNameContainingIgnoreCase(name);
}
}