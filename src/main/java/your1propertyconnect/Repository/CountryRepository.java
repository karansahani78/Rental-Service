package your1propertyconnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import your1propertyconnect.Model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByCountryName(String countryName);
}
