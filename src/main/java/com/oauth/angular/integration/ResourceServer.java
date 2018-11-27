/*package com.oauth.angular.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@EnableResourceServer
@Configuration
public class ResourceServer extends WebSecurityConfigurerAdapter {
//ResourceServerConfigurerAdapter {


	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

	
	
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	 http.
         anonymous().disable()
         .authorizeRequests()
         .antMatchers("/users/**").authenticated()
         .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    	
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.parentAuthenticationManager(authenticationManager)
                .inMemoryAuthentication()
                .withUser("Peter")
                .password("peter")
                .roles("USER");
    }

       @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/token/");
    }
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}
    
}*/