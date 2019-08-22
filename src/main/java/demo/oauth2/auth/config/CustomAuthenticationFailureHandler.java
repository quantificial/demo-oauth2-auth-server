package demo.oauth2.auth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import demo.oauth2.auth.services.CredentialService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	private CredentialService credentialService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		// need to handle all the excpetions...

		String errMsg = "";
		
		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			errMsg = "Invalid username or password.";
			
		} else if (exception instanceof LockedException) {
	        
	        log.info("LoginFailHandler the user is locked!");
	        
	        errMsg = "Account is locked";
	        
	    } else if (exception instanceof UsernameNotFoundException) {
	        log.info("LoginSuccessHandler login fail!");
	        
	        errMsg = "Username is not found";
	        
	    } else {
	        log.info("LoginFailHandler login fail!");
	        
	    }
		
		log.info("### " + request.getParameter("username"));
		
		String username = request.getParameter("username");
		if(username != null ) {
			credentialService.increaseLoginFailureCount(username);
		}
		
		request.getSession().setAttribute("message", errMsg);

		response.sendRedirect(request.getContextPath() + "/ssoLogin"); // Redirect user to login page with error message.
		
		
	}

}
