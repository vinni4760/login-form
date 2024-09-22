package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nt.service.CustomServiceUserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity request) throws Exception {
		request.authorizeHttpRequests(req->req.requestMatchers("/home").permitAll()
				.requestMatchers("/contact")
				.permitAll()
				.anyRequest()
				.authenticated())
		  .formLogin(Customizer.withDefaults());
			return request.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(CustomServiceUserDetails());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public CustomServiceUserDetails  CustomServiceUserDetails() {
		return new CustomServiceUserDetails();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
