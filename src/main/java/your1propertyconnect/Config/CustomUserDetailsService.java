/*package your1propertyconnect.Config;

import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Simulating a user data source (e.g., a database)
    private static final Map<String, String> users = Map.of(
        "user1", "$2a$10$7q3K1yP1zRKEszKPQJ3F7e.OgTICG4Nc1tBdmztSttk9prH7Zml5y", // password: password123 (BCrypt)
        "admin", "$2a$10$eCQb58Uwt0E0uv5KYZl8QO8fscff.r6cdtbOvQuHEDYpa78o/OY.q" // password: admin123 (BCrypt)
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user details from the data source
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Example: Add authorities (roles) based on username
        String password = users.get(username);
        String role = username.equals("admin") ? "ROLE_ADMIN" : "ROLE_USER";

        // Return a UserDetails object with username, password, and authorities
        return User.builder()
                   .username(username)
                   .password(password)
                   .roles(role)
                   .build();
    }
}
*/
