package your1propertyconnect.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private int propertyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "property_type_id", nullable = false) // Updated to use PropertyType entity
    private PropertyType propertyType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "area_sq_ft")
    private double areaSqFt;

    @Column(name = "bedrooms")
    private double bedrooms;

    @Column(name = "bathrooms")
    private double bathrooms;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @Column(name = "listed_at", updatable = false, nullable = false)
    private Timestamp listedAt = new Timestamp(System.currentTimeMillis());

    // Getters and Setters
    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getAreaSqFt() {
        return areaSqFt;
    }

    public void setAreaSqFt(double areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public Timestamp getListedAt() {
        return listedAt;
    }

    public void setListedAt(Timestamp listedAt) {
        this.listedAt = listedAt;
    }
}
