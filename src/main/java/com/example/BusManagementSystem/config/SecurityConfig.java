package com.example.BusManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
        httpSec.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request


                        .requestMatchers("/api/customer/addcustomer", "/api/customer/login").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")

//                        .requestMatchers(HttpMethod.GET, "/bus/**").hasAnyRole("CUSTOMER","ADMIN")
                        .requestMatchers("/bus/getalldetails").hasRole("ADMIN")
                        //.requestMatchers("/bus/**").hasRole("ADMIN")
//                        .requestMatchers("/bus/**").authenticated()

                        //.requestMatchers("/bus/**").permitAll()


                        //.requestMatchers("/api/schedule", "/api/route").hasRole("ADMIN")



                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSec.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSec.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}



