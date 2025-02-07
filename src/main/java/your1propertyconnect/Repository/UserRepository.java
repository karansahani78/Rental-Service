
//Your1PropertyConnect\src\main\java\your1propertyconnect\Dao\UserRepository.java

package your1propertyconnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import your1propertyconnect.Model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Custom method
    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String email);


    // Default methods like save(), findAll(), etc. are not specified.
}
