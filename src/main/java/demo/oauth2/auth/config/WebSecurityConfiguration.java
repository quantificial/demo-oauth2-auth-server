package demo.oauth2.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	//@Autowired
	//private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring()
//	            .antMatchers("/test");
	}
	
	private PasswordEncoder passwordEncoder;
	
	/**
	 * create the delegate password encoder to support different kind of encoding
	 * @return
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        return passwordEncoder;
    }
    
//    @Bean
//    public ServletContextTemplateResolver templateResolver() {
//    	
//        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(null);
//        resolver.setPrefix("/WEB-INF/views");
//        resolver.setSuffix(".html"); // here
//        resolver.setTemplateMode("HTML5");
//        resolver.setOrder(1);
//        return resolver;
//    }    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception { 
    	
    	// http.requestMatchers define which requests will be intercepted
    	// The requestMatchers line specifies to which requests the security check applies. The authorizeRequests line does the actual security check
        
    	// for oauth2 endpoints
    	http
        	.requestMatchers()        	        	
            	.antMatchers("/login", "/oauth/authorize","/oauth/check_token"); 

        // other endpoints
    	http.requestMatchers().antMatchers("/ssoLogin");
    	http.requestMatchers().antMatchers("/ssoLogin/process");
    	http.requestMatchers().antMatchers("/ssoAccountLocked");
        http.requestMatchers().antMatchers("/exit");
        http.requestMatchers().antMatchers("/h2","/h2/**");
        http.requestMatchers().antMatchers("/test");
        http.requestMatchers().antMatchers("/moon");
        http.requestMatchers().antMatchers("/sun");
        http.requestMatchers().antMatchers("/resources/templates/**");
        http.requestMatchers().antMatchers("/resources/**");
        http.requestMatchers().antMatchers("/webjars/**");
        
        // such that we could assign the permission
        http.authorizeRequests().antMatchers("/ssoLogin").permitAll();
        http.authorizeRequests().antMatchers("/ssoLogin/process").permitAll();
        http.authorizeRequests().antMatchers("/sooAccountLocked").permitAll();
        http.authorizeRequests().antMatchers("/test").permitAll();
        http.authorizeRequests().antMatchers("/moon").permitAll();
        http.authorizeRequests().antMatchers("/sun").permitAll();
        http.authorizeRequests().antMatchers("/h2/").permitAll();
        http.authorizeRequests().antMatchers("/h2/**").permitAll();
        http.authorizeRequests().antMatchers("/resources/templates/**").permitAll();
        http.authorizeRequests().antMatchers("/resources/**").permitAll();
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        // all other requests must be authenticated
        http
            .authorizeRequests()            
            	.anyRequest()
            	.authenticated();
                        
        // form based authentication is supported
        //http.formLogin().permitAll();
        http.formLogin().loginPage("/ssoLogin").loginProcessingUrl("/ssoLogin/process").permitAll();
        //http.formLogin().loginProcessingUrl("/perform_login");
        
        
        // create custom login form 

        // disable csrf, such that get could be used for /logout
        //http.csrf().disable();
        http.csrf().ignoringAntMatchers("/h2/**");
        
        // for h2
        http.headers().frameOptions().disable();
        
        
        //example
        //http.authorizeRequests().antMatchers(HttpMethod.GET).access("permitAll");
        //http.authorizeRequests().antMatchers("/v1/hello").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.POST, "/v1/world").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/hello").access("permitAll");
        //http.authorizeRequests().antMatchers("/v1/admin").hasRole("ADMIN");
        //http.authorizeRequests().antMatchers("/**/*.html").access("permitAll");
        //http.authorizeRequests().antMatchers("/**/*.css").access("permitAll");
        //http.authorizeRequests().antMatchers("/**/*.js").access("permitAll");
        //http.authorizeRequests().antMatchers("/**/*.png").access("permitAll");
        //http.authorizeRequests().anyRequest().authenticated();    

//		http.formLogin().failureHandler((req, res, exp) -> { // Failure handler invoked after authentication failure
//
//			// need to handle all the excpetions...
//
//			String errMsg = "";
//			if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
//				errMsg = "Invalid username or password.";
//			} else {
//				errMsg = "Unknown error - " + exp.getMessage();
//			}
//			req.getSession().setAttribute("message", errMsg);
//
//			res.sendRedirect(req.getContextPath() + "/ssoLogin"); // Redirect user to login page with error message.
//		});

        //http.formLogin().failureHandler(this.customAuthenticationFailureHandler);
		http.formLogin().successHandler(successHandler());
		http.formLogin().failureHandler(failureHandler());
    } 
    
    @Bean
    public AuthenticationFailureHandler failureHandler() {
    	CustomAuthenticationFailureHandler handler = new CustomAuthenticationFailureHandler();
    	return handler;
    }
    
    /**
     * use SavedRequestAwareAuthenticationSuccessHandler which will save the original request url and store in the session for the redirect 
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler successHandler() {
    	//SavedRequestAwareAuthenticationSuccessHandler
    	//SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
    	//SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
    	    	
    	//handler.setUseReferer(true);
        //handler.setAlwaysUseDefaultTargetUrl(true);
        //handler.setRedirectStrategy(new DefaultRedirectStrategy());
        return handler;
    }

    
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new JdbcUserDetails();
    }
       
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
    	
    	auth.userDetailsService(userDetailsServiceBean())
    		.passwordEncoder(passwordEncoder());
    	
       
    } 

}
