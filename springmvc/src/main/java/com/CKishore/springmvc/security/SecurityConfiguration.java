package com.CKishore.springmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager()
	{
		UserDetails userDetails=createUserDetails("kishore","1234");
		UserDetails userDetails1=createUserDetails("akash","12345");

		return new InMemoryUserDetailsManager(userDetails,userDetails1);
	}
		
		
		private UserDetails createUserDetails(String username,String password)
		{
				return User.builder()
				.username(username)
				.password(passwordEncoder().encode(password))
				.roles("USER")
				.build();		
		}
		
		
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain createSecurityFilterChain(HttpSecurity http)
			throws Exception
			{
				http.authorizeHttpRequests(
						auth->auth.anyRequest().authenticated());
				http.formLogin(Customizer.withDefaults());
				
				http.csrf(csrf->csrf.disable());
				
				http.headers().frameOptions().disable();
				return http.build();
			}

}
