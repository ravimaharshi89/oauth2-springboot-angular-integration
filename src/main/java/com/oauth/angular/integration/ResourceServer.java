package com.oauth.angular.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@EnableResourceServer
@Configuration
public class ResourceServer extends WebSecurityConfigurerAdapter {

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.parentAuthenticationManager(authenticationManagerBean())
				.inMemoryAuthentication()
				.withUser("Peter")
				.password("$2a$10$qYI3w9mAT5F9urb2e3mfjecK.4zfAgQJpRlxGAUy4lop4bDKoH3DW") // encrypted value of peter
				.roles("USER");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().authorizeRequests()
				.antMatchers("/rest/hello/**").authenticated().and()
				.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());

	}

}