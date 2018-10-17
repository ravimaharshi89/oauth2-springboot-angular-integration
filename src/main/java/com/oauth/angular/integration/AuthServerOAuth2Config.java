package com.oauth.angular.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {/*
  
    
	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
 
    @Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) 
      throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) 
      throws Exception {
        clients.jdbc(dataSource())
          .withClient("sampleClientId")
          .authorizedGrantTypes("implicit")
          .scopes("read").autoApprove(true)
          .and().withClient("clientIdPassword")
          .secret("secret").authorizedGrantTypes("password","authorization_code", "refresh_token")
          .scopes("read");
    }
 
    @Override
    public void configure(
      AuthorizationServerEndpointsConfigurer endpoints) 
      throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }
 
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }
    
    @Bean
    public DataSource dataSource() {
    	DataSource dataSource = null;
		try {
			javax.naming.Context initcContext = new InitialContext();
			dataSource = (DataSource) initcContext.lookup("jdbc/oauth");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	return dataSource;
    	
    }
    
*/
	static final String CLIEN_ID = "devglan-client";
	//static final String CLIENT_SECRET = "devglan-secret";
	static final String CLIENT_SECRET = "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG";
	static final String GRANT_TYPE = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
   /* @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }*/


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.
        inMemory().withClient(CLIEN_ID).secret(CLIENT_SECRET)
        		  .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                  .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                  .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                  .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new InMemoryTokenStore()).authenticationManager(authenticationManager);
    }
    

}