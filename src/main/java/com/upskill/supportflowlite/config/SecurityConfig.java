package com.upskill.supportflowlite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("harry@gmail.com")
                .password(passwordEncoder.encode("harry@123"))
                .roles("CUSTOMER")
                .build();
        UserDetails user2 = User.builder()
                .username("ronald@gmail.com")
                .password("{bcrypt}$2a$10$CZMXY/3San06wDK2ZdHHFOCySgXcAUrxwx5tb.DMU.ADEPUAyieQq")
                .roles("CUSTOMER")
                .build();
        UserDetails user3 = User.builder()
                .username("severus@gmail.com")
                .password("{bcrypt}$2a$10$aaJjRnnI2nL7pulokbbRP.eDniNJPZuww/CuaVj5KOpEieYJFJAca")
                .roles("EXECUTIVE")

                .build();
        return new InMemoryUserDetailsManager(user1, user2,user3);
    }

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/auth/login").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/customer/add/v1").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/executive/add/{userId}").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/user/add").permitAll()

                        .anyRequest().authenticated()
                )

         .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE", "OPTIONS"));
        configuration.addAllowedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
