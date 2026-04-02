package com.finance.finance.dashboard.system.config;

import com.finance.finance.dashboard.system.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC APIs
                        .requestMatchers("/auth/**").permitAll()

                        // VIEWER + ANALYST + ADMIN → dashboard
                        .requestMatchers("/dashboard/**")
                        .hasAnyRole("VIEWER", "ANALYST", "ADMIN")

                        //  ANALYST + ADMIN → read records
                        .requestMatchers(HttpMethod.GET, "/records/**")
                        .hasAnyRole("ANALYST", "ADMIN")

                        //ADMIN → create/update/delete records
                        .requestMatchers(HttpMethod.POST, "/records/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/records/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/records/**")
                        .hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}