package com.api.spring_security.config;

import com.api.spring_security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableWebSecurity //Tells spring-security to don't use the default configuration
public class SecurityConfig {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(customizer-> customizer.disable()); // Disable csrf
        //security.authorizeHttpRequests(requests->requests.anyRequest().authenticated()); // we won't be able to open localhost in incognito mode
        security.authorizeHttpRequests(requests->requests.requestMatchers("register ").permitAll().anyRequest().authenticated()); // This will not authenticate login and register request. All other requests will be authenticated
        security.formLogin(Customizer.withDefaults()); // For returning a form
        security.httpBasic(Customizer.withDefaults()); // Adding this as in the above line we are returning a form but for Postman we need this property
        security.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Make the session StateLescs
        security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //Using No Password encoder
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        //Using BCryptPasswordEncoder as we will validate the password of user.
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    //AuthenticationManager will talk to AuthenticationProvider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
