package by.it_academy.jd2._107.user_service.config;

import by.it_academy.jd2._107.user_service.controller.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter) throws Exception {
        // Enable CORS and disable CSRF
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());

        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(
                                (request, response, ex) ->
                                        response.setStatus(
                                                HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((request, response, ex) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                );

        http

                .authorizeHttpRequests(authz -> authz

                        .requestMatchers(HttpMethod.GET, "/api/v1/cabinet/me").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/{uuid}").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/{uuid}/dt_update/{dt_update}").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cabinet/registration").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/cabinet/verification/{code}/{mail}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/cabinet/login").permitAll()
                        .anyRequest().authenticated()

                );
        http
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
