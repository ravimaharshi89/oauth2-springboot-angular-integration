package com.oauth.angular.integration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.oauth.angular.service.AccountUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
	
	private AccountUserDetailsService accountUserDetailsService = new AccountUserDetailsService();

	/*
	 * static final String CLIEN_ID = "devglan-client"; //static final String
	 * CLIENT_SECRET = "devglan-secret"; static final String CLIENT_SECRET =
	 * "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG"; static
	 * final String GRANT_TYPE = "password"; static final String
	 * AUTHORIZATION_CODE = "authorization_code"; static final String
	 * REFRESH_TOKEN = "refresh_token"; static final String IMPLICIT =
	 * "implicit"; static final String SCOPE_READ = "read"; static final String
	 * SCOPE_WRITE = "write"; static final String TRUST = "trust"; static final
	 * int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60; static final int
	 * FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	 */

	@Autowired
    private AuthenticationManager authenticationManager;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.database.driverClassName}")
	private String dbDriverClassName;

	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl(datasourceUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);

		return dataSource;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()");
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource());
	}

	/*
	 * @Override public void configure(ClientDetailsServiceConfigurer clients)
	 * throws Exception {
	 * clients.jdbc(dataSource).withClient(CLIEN_ID).secret(CLIENT_SECRET)
	 * .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN,
	 * IMPLICIT) .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
	 * .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
	 * .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS); }
	 */
	
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource());
    }

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).userDetailsService(accountUserDetailsService);
	}

}