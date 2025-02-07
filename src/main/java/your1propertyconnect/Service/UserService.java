package your1propertyconnect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import your1propertyconnect.Model.SubscriptionTier;
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

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user); // Update user in the database
    }

    // New method to update subscription tier
    public boolean updateSubscriptionTier(Integer userId, String subscriptionTier) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Set the new subscription tier
            user.setSubscriptionTier(SubscriptionTier.valueOf(subscriptionTier));  // Assuming SubscriptionTier is an enum
            userRepository.save(user);  // Save the updated user to the database
            return true;  // Successfully updated the subscription tier
        }
        return false;  // User not found
    }
}
