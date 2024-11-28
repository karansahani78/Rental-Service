package your1propertyconnect.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses") // Explicitly map to the "addresses" table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    private String street;
    private String city;

    @Column(name = "postal_code") // Map to "postal_code" in the database
    private String postalCode;

    @Column(name = "province_code", length = 10)
    private String provinceCode;

    @Column(name = "country_code", length = 2, nullable = false)
    private String countryCode;

    public Address() {
    }

    public Address(int addressId, String street, String city, String provinceCode, String postalCode,
            String countryCode) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.provinceCode = provinceCode;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
