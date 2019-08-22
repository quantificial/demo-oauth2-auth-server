package demo.oauth2.auth.config;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import demo.oauth2.auth.model.Authority;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.repository.CredentialRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcUserDetails implements UserDetailsService{

    @Autowired
    private CredentialRepository credentialRepository;
    
    public static String getRequestRemoteAddr(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
                   .getRequest();         
        return request.getRemoteAddr();
    }        

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialRepository.findByName(username);


        if(credentials==null){

        	log.info("username not found: " + username);
            throw new UsernameNotFoundException("User"+username+"can not be found");
        }

        User user = new User(credentials.getName(), 
        		credentials.getPassword(),
        		credentials.isEnabled(),
        		//true, // account non-expired
        		//true, // credentialsNonExpired 
        		//true, // accountNonLocked 
        		!credentials.isAccountExpired(),
        		!credentials.isCredentialsExpired(),
        		!credentials.isAccountLocked(),
        		credentials.getAuthorities());
        
        log.info("## Authority ##########################################");
        for(Authority s : credentials.getAuthorities()) {
        	log.info(s.getAuthority());
        }
        
        log.info("## IP ##########################################");
        log.info(JdbcUserDetails.getRequestRemoteAddr());
        
        return  user;

    }
}
