package your1propertyconnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import your1propertyconnect.Model.Province;
import your1propertyconnect.Repository.ProvinceRepository;

import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public List<Province> getProvincesByCountryCode(String countryCode) {
        return provinceRepository.findByCountryCode(countryCode);
    }

    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }
}
