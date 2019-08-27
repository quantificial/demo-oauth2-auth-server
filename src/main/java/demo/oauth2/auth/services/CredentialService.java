package demo.oauth2.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import demo.oauth2.auth.AppConstants;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.repository.CredentialsRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CredentialService {
	
	@Autowired
	private CredentialsRepository credentialRepository;
	
    @Autowired    
    private PasswordEncoder passwordEncoder;
    
    
    public boolean verifyPassword(String username, String password) {
    	    	   
		log.info("### username " + username);
		Credentials user = credentialRepository.findByName(username);
		
		if(user != null) {
					
			if(passwordEncoder.matches(password, user.getPassword()) == true) {
				log.info("password match");
																			
				
				return true;
				
			}else {
				log.info("password not match");
			}
    	
		}
    	
    	return false;    	
    }
    
    public boolean updatePassword(String username, String password) {
    	
		log.info("### username " + username);
		Credentials user = credentialRepository.findByName(username);
		
		if(user != null) {

			
			String encodedPassword = passwordEncoder.encode(password);
			
			user.setPassword(encodedPassword);
			
			credentialRepository.save(user);
			
			
			log.info("password updated");
			
			return true;
    	
		}
    	
    	return false;    	
    	
    }
	
	
	/**
	 * increase the login failure count
	 * 
	 * @param name
	 * @return whether the account has been locked or not
	 */
	public boolean increaseLoginFailureCount(String name) {
		
		log.info("### username " + name);
		Credentials user = credentialRepository.findByName(name);
		
		if(user != null) {
			int loginFailureCount = user.getLoginFailureCount() + 1;
			user.setLoginFailureCount(loginFailureCount);
			log.info("failure count: " + loginFailureCount);
			
			if(loginFailureCount >= AppConstants.LOGIN_FAILURE_COUNT_LIMIT) {
				user.setAccountLocked(true);	
				credentialRepository.save(user);
				return true;
			}
			
			credentialRepository.save(user);
		}
		
		return false;
	}
	
	
	/**
	 * check whether the password fulfilled the policy or not
	 * 
	 * @param password
	 * @return
	 */
	public boolean isFulfilledPasswordPolicy(String password) {
		
	      //total score of password
        int passwordScore = 0;
        
        if( password.length() < 8 )
            return false;       
        
        //if it contains one digit, add 1 to total score
        if( password.matches("(?=.*[0-9]).*") )
        	passwordScore += 1;
        
        //if it contains one lower case letter, add 1 to total score
        if( password.matches("(?=.*[a-z]).*") )
        	passwordScore += 1;
        
        //if it contains one upper case letter, add 1 to total score
        if( password.matches("(?=.*[A-Z]).*") )
        	passwordScore += 1;    
        
        //if it contains one special character, add 2 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
        	passwordScore += 1;
        
        
        if(passwordScore >= 2) {
        	return true;
        }
				
		return false;
	}
}
