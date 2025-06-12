package tn.esprit.examen.nomPrenomClasseExamen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.UserRepository;

import static org.springframework.security.core.userdetails.User.withUsername;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/credits/**").hasAnyRole("ADMIN", "AGENT")
                        .requestMatchers("/api/stats/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {
                }); // Nouvelle syntaxe

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            tn.esprit.examen.nomPrenomClasseExamen.entities.User appUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable : " + username));

            return org.springframework.security.core.userdetails.User.withUsername(appUser.getUsername())
                    .password("{noop}" + appUser.getPassword())
                    .roles(appUser.getRole().toUpperCase())
                    .build();
        };
    }

}
