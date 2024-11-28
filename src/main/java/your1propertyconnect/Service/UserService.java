// Your1PropertyConnect\src\main\java\your1propertyconnect\Service\UserService.java

package your1propertyconnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import your1propertyconnect.Model.User;
import your1propertyconnect.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public Optional<User> loginUser(String email) {
        return userRepository.findByEmail(email);
    }
}
