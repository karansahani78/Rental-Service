package your1propertyconnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import your1propertyconnect.Model.*;
import your1propertyconnect.Repository.ProvinceRepository;
import your1propertyconnect.Service.PropertyService;
import your1propertyconnect.Service.AddressService;
import your1propertyconnect.Service.PropertyTypeService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final AddressService addressService;
    private final PropertyTypeService propertyTypeService;
    private final ProvinceRepository provinceRepository;

    @Autowired
    public PropertyController(PropertyService propertyService, AddressService addressService,
            PropertyTypeService propertyTypeService, ProvinceRepository provinceRepository) {
        this.propertyService = propertyService;
        this.addressService = addressService;
        this.propertyTypeService = propertyTypeService;
        this.provinceRepository = provinceRepository;
    }

    @PostMapping("/add")
    public String addProperty(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String propertyTypeName,
            @RequestParam BigDecimal price,
            @RequestParam double areaSqFt,
            @RequestParam double bedrooms,
            @RequestParam double bathrooms,
            @RequestParam String street,
            @RequestParam String city,
            @RequestParam String province,
            @RequestParam String postalCode,
            @RequestParam String country,
            @RequestParam MultipartFile[] images,
            HttpSession session) throws IOException {

        // Check if the user is logged in
        System.out.println("DEBUG: Checking session for user ID...");
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            System.out.println("DEBUG: User ID not found in session!");
            return "redirect:/sign_in.html?error=not_logged_in";
        }
        System.out.println("DEBUG: User ID found in session: " + userId);

        // DEBUG: Print input values
        System.out.println("DEBUG: Inputs received - ");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Property Type: " + propertyTypeName);
        System.out.println("Price: " + price);
        System.out.println("Area (sq ft): " + areaSqFt);
        System.out.println("Bedrooms: " + bedrooms);
        System.out.println("Bathrooms: " + bathrooms);
        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("State/Province: " + province);
        System.out.println("Postal Code: " + postalCode);
        System.out.println("Country: " + country);

        // Save images
        System.out.println("DEBUG: Saving images...");
        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists() && !uploadFolder.mkdir()) {
            throw new IOException("Failed to create upload directory: " + uploadDir);
        }

        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                File file = new File(uploadDir + fileName);
                image.transferTo(file);
                System.out.println("DEBUG: Image saved: " + fileName);
            }
        }

        // Save Address
        System.out.println("DEBUG: Saving address...");
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setProvinceCode(province);
        address.setPostalCode(postalCode);
        address.setCountryCode(country);
        Address savedAddress = addressService.saveAddress(address);
        System.out.println("DEBUG: Address saved with ID: " + savedAddress.getAddressId());

        // Fetch PropertyType
        PropertyType propertyType = propertyTypeService.getPropertyTypeByName(propertyTypeName);
        if (propertyType == null) {
            throw new IllegalArgumentException("Invalid property type: " + propertyTypeName);
        }

        // Save Property
        System.out.println("DEBUG: Saving property...");
        Property property = new Property();
        property.setTitle(title);
        property.setDescription(description);
        property.setPropertyType(propertyType);
        property.setPrice(price);
        property.setAreaSqFt(areaSqFt);
        property.setBedrooms(bedrooms);
        property.setBathrooms(bathrooms);
        property.setStatus(PropertyStatus.AVAILABLE);
        property.setAddress(savedAddress);
        User user = new User();
        user.setId(userId);
        property.setUser(user);

        Property savedProperty = propertyService.saveProperty(property);
        System.out.println("DEBUG: Property saved with ID: " + savedProperty.getPropertyId());
        return "<script>alert('Property added successfully!'); window.location.href='/';</script>";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserProperties(HttpSession session) {
        // Check if user is logged in
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\":\"User not logged in\"}");
        }

        // Fetch properties for the user
        List<Property> userProperties = propertyService.getPropertiesByUserId(userId);
        return ResponseEntity.ok(userProperties);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getPropertyById(@PathVariable int propertyId) {
        Optional<Property> property = propertyService.findById(propertyId);
        return property.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public ResponseEntity<Property> updateProperty(@RequestBody Property property, HttpSession session) {
        // Check if the user is logged in
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Check if the property exists
        Optional<Property> existingPropertyOptional = propertyService.findById(property.getPropertyId());
        if (existingPropertyOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found");
        }

        Property existingProperty = existingPropertyOptional.get();

        // Ensure the user owns the property
        if (existingProperty.getUser().getId() != userId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You do not own this property");
        }

        // Update property details
        existingProperty.setTitle(property.getTitle());
        existingProperty.setDescription(property.getDescription());
        existingProperty.setPrice(property.getPrice());
        existingProperty.setAreaSqFt(property.getAreaSqFt());
        existingProperty.setBedrooms(property.getBedrooms());
        existingProperty.setBathrooms(property.getBathrooms());
        existingProperty.setStatus(property.getStatus());

        // Update address if provided
        if (property.getAddress() != null) {
            Address updatedAddress = property.getAddress();
            existingProperty.getAddress().setStreet(updatedAddress.getStreet());
            existingProperty.getAddress().setCity(updatedAddress.getCity());
            existingProperty.getAddress().setPostalCode(updatedAddress.getPostalCode());
            existingProperty.getAddress().setCountryCode(updatedAddress.getCountryCode());

            // Handle provinceCode based on countryCode
            String countryCode = updatedAddress.getCountryCode();
            if ("US".equals(countryCode) || "CA".equals(countryCode)) {
                if (updatedAddress.getProvinceCode() == null
                        || !provinceRepository.existsByProvinceCode(updatedAddress.getProvinceCode())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Invalid or missing province code for US or CA");
                }
                existingProperty.getAddress().setProvinceCode(updatedAddress.getProvinceCode());
            } else {
                existingProperty.getAddress().setProvinceCode(null); // Set to null for other countries
            }
        }

        // Fetch and set PropertyType
        if (property.getPropertyType() != null && property.getPropertyType().getTypeName() != null) {
            PropertyType propertyType = propertyTypeService
                    .getPropertyTypeByName(property.getPropertyType().getTypeName());
            if (propertyType == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid property type");
            }
            existingProperty.setPropertyType(propertyType);
        }

        Property updatedProperty = propertyService.saveProperty(existingProperty);

        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Integer propertyId, HttpSession session) {
        // Check if the user is logged in
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Check if the property exists
        Optional<Property> propertyOptional = propertyService.findById(propertyId);
        if (propertyOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Property property = propertyOptional.get();

        // Ensure the user owns the property
        if (property.getUser().getId() != userId) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Delete the property
        propertyService.deletePropertyById(propertyId);
        return ResponseEntity.noContent().build();
    }

}
