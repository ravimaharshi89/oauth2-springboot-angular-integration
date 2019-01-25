package com.oauth.angular;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.oauth.angular.service.AccountUserDetailsService;

@EnableResourceServer
@Configuration
@EnableWebSecurity
public class ResourceServer extends WebSecurityConfigurerAdapter {

	/*@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.database.driverClassName}")
	private String dbDriverClassName;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;*/

	
	@Autowired
	private DataSource dataSource1;
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*@Bean
	@Primary
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl(datasourceUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);

		return dataSource;
	}*/
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManagerBean()).jdbcAuthentication()
			.dataSource(dataSource1).and().userDetailsService
			(new AccountUserDetailsService()).passwordEncoder(encoder());
	}
	
	/*@Override
	public void configure(HttpSecurity http) throws Exception {
		http.anonymous().disable().authorizeRequests()
				.antMatchers("/rest/hello/**","/rest/hello/fetch-otp/**").authenticated().and()
				.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

    @Override
    public void configure(HttpSecurity http) throws Exception {
                    http.authorizeRequests().antMatchers("/rest/hello/**").permitAll().anyRequest().fullyAuthenticated();
    }*/

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/rest/hello/fetch-otp/**", "/rest/oauth/token");
    }
	
	
}