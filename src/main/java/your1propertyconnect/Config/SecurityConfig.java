/*
package your1propertyconnect.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpSession;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register", "/index").permitAll() // Allow access to these endpoints
                .anyRequest().authenticated() // All other endpoints require authentication
            )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page
                .defaultSuccessUrl("/index", true)
                .permitAll()
                .failureUrl("/login?error=true")  // Redirect to login page on failure with an error query parameter
                .successHandler((request, response, authentication) -> {
                    // Store user information in the session on successful login
                    UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", principal.getId());  // Store user ID in session
                    session.setAttribute("email", principal.getEmail());  // Store username in session
                    response.sendRedirect("/index");  // Redirect to index page after successful login
                })
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

*/