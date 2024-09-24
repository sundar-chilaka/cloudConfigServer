package com.sundar.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	//taking Spring UserDetailsService interface
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		//taking Spring UserDetails interface
		UserDetails  admin =User.withUsername("admin")
								.password(encoder.encode("admin"))
								.roles("ADMIN")
								.build();
		
		UserDetails  user =User.withUsername("user")
				.password(encoder.encode("user"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);
	} 
	
	//role based 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/prodect/welcome","/prodect/save")
				.permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/prodect/**")
				.authenticated()
				.and()
				.formLogin().and().build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

