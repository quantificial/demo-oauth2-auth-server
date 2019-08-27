package demo.oauth2.auth.config;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.model.Department;
import demo.oauth2.auth.repository.CredentialsRepository;
import demo.oauth2.auth.repository.DepartmentRepository;

@RestController
public class UserController {
	
	@Autowired
	private CredentialsRepository credentialRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
    
    @RequestMapping("/test")
    public String test() {
    	
    	Department searchDept = departmentRepository.findByName("IT");
    	
		for(Credentials c : searchDept.getCredentialsSet()) {
			System.out.println(c.getName());
		}
		
    	return "test";
    }
}
