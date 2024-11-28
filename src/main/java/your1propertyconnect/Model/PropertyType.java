package your1propertyconnect.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "property_type")
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_type_id")
    private int id;

    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
