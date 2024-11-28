package your1propertyconnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import your1propertyconnect.Model.Province;
import your1propertyconnect.Service.ProvinceService;

import java.util.List;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/{countryCode}")
    public List<Province> getProvincesByCountryCode(@PathVariable String countryCode) {
        return provinceService.getProvincesByCountryCode(countryCode);
    }

    @PostMapping("/add")
    public Province addProvince(@RequestBody Province province) {
        return provinceService.saveProvince(province);
    }

}
