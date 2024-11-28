package your1propertyconnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import your1propertyconnect.Model.Province;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findByCountryCode(String countryCode);

    // Check if a province exists by its code
    boolean existsByProvinceCode(String provinceCode);
}
