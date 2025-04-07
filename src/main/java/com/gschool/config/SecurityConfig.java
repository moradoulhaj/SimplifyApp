package com.gschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (use cautiously in production)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // 🚀 Allow all requests, disabling authentication
                )
                .formLogin(form -> form.disable()) // ❌ Disable default login page
                .httpBasic(httpBasic -> httpBasic.disable()) // ❌ Disable Basic Authentication
                .cors() // Enable CORS configuration in Spring Security
                .and()
                .sessionManagement()
                .disable(); // Disable session management

        return http.build();
    }

    // Configuring CORS globally
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Allowing GitHub Pages as a valid origin
                .allowedOrigins("https://5173-idx-mergergit-1730719179959.cluster-rz2e7e5f5ff7owzufqhsecxujc.cloudworkstations.dev",
                        "https://moradoulhaj.github.io")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
