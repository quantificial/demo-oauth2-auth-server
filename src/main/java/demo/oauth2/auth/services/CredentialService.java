package demo.oauth2.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.oauth2.auth.AppConstants;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.repository.CredentialRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CredentialService {
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	
	/**
	 * increase the login failure count
	 * 
	 * @param name
	 * @return
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
}
