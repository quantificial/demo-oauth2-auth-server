package demo.oauth2.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
    private Environment environment;	
	
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        
    	String keyStorePassword = environment.getProperty("keystore.password");
        String keyStoreLocation = environment.getProperty("keystore.location");
        String keyStoreAlias = environment.getProperty("keystore.alias");
        
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource(keyStoreLocation),
                keyStorePassword.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyStoreAlias));
        
        return converter;
    }	
    
    /**
     * create the JWT Token Store
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }    
    
//  @Bean
//  public TokenStore inMemoryTokenStore() {
//      return new InMemoryTokenStore();
//  }
    
//  @Bean
//  public TokenStore tokenStore() {
//      return new JdbcTokenStore(oauthDataSource());
//  }
    
    
    		
    
//    @Autowired    
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired    
    private PasswordEncoder passwordEncoder;

    
    // h2 datasource
    //@Autowired
    //private DataSource dataSource;
    
    
    /**
     * create datasource from spring configuration
     * 
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
    }

    
    // create OAuth2 JDBC client detail service
    @Bean(name="jdbcClientDetailsService")
    public JdbcClientDetailsService clientDetailsService() {
    	JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(oauthDataSource());
    	jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);
        return jdbcClientDetailsService;
    }    
        
                
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(oauthDataSource());
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(oauthDataSource());
    }
            
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
        
    
    /**
     * configure token store
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
         		.tokenStore(tokenStore())
         		.tokenEnhancer(jwtTokenEnhancer())
         		.approvalStore(approvalStore())
                .authenticationManager(authenticationManager);
    }    

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    	
    	// use JDBC client details    	
    	 clients.withClientDetails(clientDetailsService());
    	     	    	
    	// System.out.println(passwordEncoder.encode("secret"));
    	// $2a$10$w6U0a421YvG8GuVXeVcyk..7Kba/esmX0jNwV2jYYwedKC2CIT7Qy    	
    	    	
    	/*
        clients.inMemory()
            .withClient("app1")
            .secret(passwordEncoder.encode("secret"))
            .authorizedGrantTypes("authorization_code")
            .scopes("user_info")
            .autoApprove(true)            
            .redirectUris("http://localhost:8082/ui/login")
            .accessTokenValiditySeconds(3600)    
            .and()
	        .withClient("app2")
	        .secret(passwordEncoder.encode("secret"))
	        .authorizedGrantTypes("authorization_code")
	        .scopes("user_info")
	        .autoApprove(true)
	        .redirectUris("http://localhost:8083/ui2/login")
	        .accessTokenValiditySeconds(3600)                                    
        ;
        */
        
    }

}
