Hands-on 1 Query Methods

1. findByNameContainingIgnoreCase("ou")
2. findByNameContainingIgnoreCaseOrderByNameAsc("ou")
3. findByNameStartingWithIgnoreCase("Z")

Sample test calls for OrmLearnApplication:

countryService.searchCountries("ou");
countryService.searchCountriesSorted("ou");
countryService.searchCountriesStartingWith("Z");
