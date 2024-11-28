package your1propertyconnect.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private int provinceId;

    @Column(name = "province_name", nullable = false, unique = true)
    private String provinceName;

    @Column(name = "province_code", length = 10, nullable = false, unique = true)
    private String provinceCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    // Getters and Setters
    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
