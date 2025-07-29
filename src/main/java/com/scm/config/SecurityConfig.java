package com.scm.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.scm.config.jwt.JwtValidator;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .sessionManagement(session ->{
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .csrf(csrf ->{
                csrf.disable();
            })
            .cors(cors ->{
                cors.configurationSource(configurationSource());
            })
            .authorizeHttpRequests(auth->{
                auth.anyRequest().permitAll();
            })
            .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class);
        return http.build();
    }

    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
        }
