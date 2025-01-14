package com.anime.tracker.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.requestMatchers("/users").permitAll()
		.requestMatchers("/animes").authenticated()
		.and()
		.httpBasic()
		.and()
        .csrf().disable(); // Disable CSRF (if needed)

		return http.build();
	}

	@Bean
    public UserDetailsService userDetailsService() {
        // In-memory user details service (you can replace it with database-backed service)
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Add a user with a username and password (plaintext passwords are for demo purposes only)
        manager.createUser(User.withUsername("user")
                .password("{noop}password")  // {noop} is used for plain-text passwords (not recommended for production)
                .roles("USER")
                .build());

        // Add an admin user (optional)
        manager.createUser(User.withUsername("admin")
                .password("{noop}adminpassword")  // Plain-text password for demo purposes
                .roles("ADMIN")
                .build());

        return manager;
    }
}