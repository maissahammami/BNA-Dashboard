package tn.esprit.examen.nomPrenomClasseExamen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/credits/**").hasAnyRole("ADMIN", "AGENT")
                        .requestMatchers("/api/stats/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(); // Ou JWT si tu veux plus tard
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password("{noop}" + user.getPassword()) // {noop} = pas d'encodage pour l'instant
                    .roles(user.getRole()) // ex: "ADMIN" ou "AGENT"
                    .build();
        };
    }
}
