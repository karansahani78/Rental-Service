package your1propertyconnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import your1propertyconnect.Model.Country;
import your1propertyconnect.Repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public String getCountryCode(String countryName) {
        Country country = countryRepository.findByCountryName(countryName);
        if (country != null) {
            return country.getCountryCode();
        } else {
            throw new IllegalArgumentException("Invalid country name: " + countryName);
        }
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
