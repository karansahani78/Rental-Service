package your1propertyconnect.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import your1propertyconnect.Model.Property;
import your1propertyconnect.Repository.PropertyRepository;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> getPropertiesByUserId(int userId) {
        return propertyRepository.findByUserId(userId);
    }

    public Property getPropertyById(int propertyId) {
        return propertyRepository.findById(propertyId).orElse(null);
    }

    public void deletePropertyById(Integer propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    public Optional<Property> findById(int propertyId) {
        return propertyRepository.findById(propertyId);
    }

}
