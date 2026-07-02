package com.cognizant.ormlearn;
import java.util.List;
import org.slf4j.*; import org.springframework.boot.*; import org.springframework.boot.autoconfigure.*;
import org.springframework.context.ApplicationContext;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
@SpringBootApplication
public class OrmLearnApplication{
private static final Logger LOGGER=LoggerFactory.getLogger(OrmLearnApplication.class);
private static CountryService countryService;
public static void main(String[] args){
ApplicationContext context=SpringApplication.run(OrmLearnApplication.class,args);
countryService=context.getBean(CountryService.class);
testGetAllCountries(); testFindCountry(); testAddCountry(); testUpdateCountry(); testDeleteCountry();
}
private static void testGetAllCountries(){ List<Country> countries=countryService.getAllCountries(); LOGGER.info(countries.toString()); }
private static void testFindCountry(){ try{ LOGGER.info(countryService.findCountryByCode("IN").toString()); }catch(Exception e){e.printStackTrace();}}
private static void testAddCountry(){ countryService.addCountry(new Country("ZZ","Test Country")); }
private static void testUpdateCountry(){ try{ countryService.updateCountry("ZZ","Updated Test Country"); }catch(Exception e){e.printStackTrace();}}
private static void testDeleteCountry(){ countryService.deleteCountry("ZZ"); }
}