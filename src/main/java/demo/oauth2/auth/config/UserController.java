package demo.oauth2.auth.config;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.model.Department;
import demo.oauth2.auth.model.Organization;
import demo.oauth2.auth.repository.CredentialsRepository;
import demo.oauth2.auth.repository.DepartmentRepository;
import demo.oauth2.auth.repository.OrganizationRepository;

@RestController
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
    public String test() {
    			
		Organization o = organizationRepository.findByName("TEST ORG");
		
		System.out.println(o.getName());
		
		
		for(Department iter : o.getDepartmentSet()) {
			
			System.out.println(iter.getName());
			
			for(Credentials c : iter.getCredentialsSet()) {
				System.out.println(c.getName());
			}
		}
		
		
		
    	return "test";
    }
}
