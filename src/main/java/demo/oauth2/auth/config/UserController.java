package demo.oauth2.auth.config;

import java.security.Principal;
import java.util.List;

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
		
    	return "test";
    }
}
