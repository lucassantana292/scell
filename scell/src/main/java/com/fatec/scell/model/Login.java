package com.fatec.scell.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Login extends WebSecurityConfigurerAdapter {
	
/*	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
*/
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
		}
		
		@Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        http
	          .csrf().disable()
	          .authorizeRequests()          
	          .anyRequest().authenticated()
	          .and()
	          .formLogin()          
	          .defaultSuccessUrl("/", true) 
	          .and()
	          .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	    }
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
