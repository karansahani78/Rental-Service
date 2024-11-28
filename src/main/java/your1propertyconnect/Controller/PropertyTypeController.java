package your1propertyconnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import your1propertyconnect.Model.PropertyType;
import your1propertyconnect.Service.PropertyTypeService;

import java.util.List;

@RestController
@RequestMapping("/property-types")
public class PropertyTypeController {

    private final PropertyTypeService propertyTypeService;

    @Autowired
    public PropertyTypeController(PropertyTypeService propertyTypeService) {
        this.propertyTypeService = propertyTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyType>> getAllPropertyTypes() {
        List<PropertyType> propertyTypes = propertyTypeService.getAllPropertyTypes();
        return ResponseEntity.ok(propertyTypes);
    }
}
