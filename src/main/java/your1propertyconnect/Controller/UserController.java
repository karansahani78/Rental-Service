package your1propertyconnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import your1propertyconnect.Model.SubscriptionTier;
import your1propertyconnect.Model.User;
import your1propertyconnect.Service.UserService;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public void register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam(required = false) String tel,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model,
            HttpServletResponse response) throws IOException {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            response.sendRedirect("/sign_up.html");
            return;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(tel);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setSubscriptionTier(SubscriptionTier.NOTIER);

        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            response.sendRedirect("/sign_in.html");

        } else {
            model.addAttribute("error", "Email already taken");
            response.sendRedirect("/sign_up.html");
        }
    }

    // Add session management
    @PostMapping("/login")
    public void login(@RequestParam String email, @RequestParam String password, HttpServletResponse response,
            HttpSession session) throws IOException {
        Optional<User> user = userService.loginUser(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            // Save user information in the session
            User loggedInUser = user.get();
            session.setAttribute("userId", loggedInUser.getId());
            session.setAttribute("userName", loggedInUser.getFirstName());
            session.setAttribute("userLastName", loggedInUser.getLastName());
            session.setAttribute("userEmail", loggedInUser.getEmail());
            session.setAttribute("userPhoneNumber", loggedInUser.getPhoneNumber());
            session.setAttribute("userSubscriptionTier", loggedInUser.getSubscriptionTier().toString());

            response.sendRedirect("/user_dashboard.html"); // Redirect to dashboard
        } else {
            response.sendRedirect("/sign_in.html?error=invalid_credentials");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate(); // Clear the session
        response.sendRedirect("/sign_in.html"); // Redirect to sign-in page
    }

    @GetMapping("/check-session")
    @ResponseBody
    public ResponseEntity<?> checkSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");
        String userLastName = (String) session.getAttribute("userLastName");
        String userEmail = (String) session.getAttribute("userEmail");
        String userPhoneNumber = (String) session.getAttribute("userPhoneNumber");
        String userSubscriptionTier = (String) session.getAttribute("userSubscriptionTier");

        if (userId == null || userName == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"Not logged in\"}");
        }

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "userName", userName,
                "lastName", userLastName,
                "email", userEmail,
                "phoneNumber", userPhoneNumber,
                "subscriptionTier", userSubscriptionTier));
    }

}