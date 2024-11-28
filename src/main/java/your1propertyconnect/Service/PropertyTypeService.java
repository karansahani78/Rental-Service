package your1propertyconnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import your1propertyconnect.Model.PropertyType;
import your1propertyconnect.Repository.PropertyTypeRepository;

import java.util.List;

@Service
public class PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    @Autowired
    public PropertyTypeService(PropertyTypeRepository propertyTypeRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
    }

    public PropertyType getPropertyTypeByName(String typeName) {
        return propertyTypeRepository.findByTypeName(typeName);
    }

    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeRepository.findAll();
    }
}
