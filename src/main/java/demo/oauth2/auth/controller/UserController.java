package demo.oauth2.auth.controller;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;

import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.model.Department;
import demo.oauth2.auth.model.Organization;
import demo.oauth2.auth.repository.CredentialsRepository;
import demo.oauth2.auth.repository.DepartmentRepository;
import demo.oauth2.auth.repository.OrganizationRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@EnableCaching
public class UserController {
	
	@Autowired
	private CredentialsRepository credentialRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
    
    @RequestMapping("/test")
    public String test(HttpSession session) {
    			
		List<Organization> oList = organizationRepository.findAll();
		
		for(Organization oIter : oList) {
			
			System.out.println("Organization: " + oIter.getName());
		
		
			for(Department iter : oIter.getDepartmentSet()) {
				
				System.out.println("Department: " + iter.getName());
				
				for(Credentials c : iter.getCredentialsSet()) {
					System.out.println("Credentials: " + c.getName());
				}
			}
		
		}
		
	      Object value = Hazelcast.getHazelcastInstanceByName("hazelcast-instance").getMap("instruments").get("hello");
	      
	       if (Objects.isNull(value)) {
	           Hazelcast.getHazelcastInstanceByName("hazelcast-instance").getMap("instruments").put("hello", "world!");
	       }        
	       
	       log.info("From Cache key=hello,value={}", value);
	       
	       String sessionId = session.getId();        
	       
	       if(session.getAttribute("secret") == null) {
	    	   log.info("setting secret...");
	    	   session.setAttribute("secret", "this is secret message");
	       }
	       
	    	String secret = (String)session.getAttribute("secret");
	    	log.info("secret={} {}", secret, "...");
	       
	       
	       log.info("sessionId={}", sessionId);	       
		
    	return "test";
    }
}
