package your1propertyconnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import your1propertyconnect.Model.Address;
import your1propertyconnect.Repository.AddressRepository;
import your1propertyconnect.Service.CountryService;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CountryService countryService;

    @Autowired
    public AddressService(AddressRepository addressRepository, CountryService countryService) {
        this.addressRepository = addressRepository;
        this.countryService = countryService;
    }

    public Address saveAddress(Address address) {
        // Directly set the country code
        address.setCountryCode(address.getCountryCode());

        System.out.println("Saving address: " + address);
        return addressRepository.save(address);
    }

}