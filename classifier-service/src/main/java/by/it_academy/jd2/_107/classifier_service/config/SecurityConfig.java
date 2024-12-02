package by.it_academy.jd2._107.classifier_service.config;

import by.it_academy.jd2._107.classifier_service.controller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception  {
        // Enable CORS and disable CSRF
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())

                // Set session management to stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Set unauthorized requests exception handler
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(
                                (request, response, ex) ->
                                        response.setStatus(
                                                HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((request, response, ex) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                )

                .authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.POST,
                                "/api/v1/classifier/currency").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/classifier/operation/category").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/classifier/currency").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/classifier/operation/category").permitAll()
                        .anyRequest().authenticated())

        // Add JWT token filter
                .addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}