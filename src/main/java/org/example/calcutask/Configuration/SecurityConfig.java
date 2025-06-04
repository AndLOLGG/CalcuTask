//package org.example.calcutask.Configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/app/h2-console/**")) // Disable CSRF for H2 console
//                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // Allow frames for H2 console
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**", "/app/h2-console/**").permitAll() // Allow access to H2 console
//                        .anyRequest().authenticated() // Authenticate all other requests
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//}