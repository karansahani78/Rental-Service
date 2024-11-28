package your1propertyconnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import your1propertyconnect.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
