package com.biblioteca.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.biblioteca.online.auth.handler.LoginSuccessHandler;
import com.biblioteca.online.models.service.JpaUserDetailsService;



@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/images/**","/bootstrap/**").permitAll()
		.antMatchers("/usuarios/**", "/libros/**", "/prestamos/**").hasAnyRole("ADMINISTRADOR", "BIBLIOTECARIO")
		.anyRequest().authenticated()
		.and()
			.formLogin()
				.successHandler(successHandler)
				.loginPage("/login")
			.permitAll()
		.and()
			.logout().permitAll()
		.and()
			.exceptionHandling().accessDeniedPage("/error_403");
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
//		UserBuilder users = User.withDefaultPasswordEncoder();
		
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
//		build.inMemoryAuthentication()
//		.withUser(users.username("aa").password("123").roles("ADMINISTRADOR"));
		
		build.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
}
