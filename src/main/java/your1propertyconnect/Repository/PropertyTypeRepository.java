package your1propertyconnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import your1propertyconnect.Model.PropertyType;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Integer> {
    PropertyType findByTypeName(String typeName);
}
