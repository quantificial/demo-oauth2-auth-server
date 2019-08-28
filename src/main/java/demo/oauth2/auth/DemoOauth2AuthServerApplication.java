package demo.oauth2.auth;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import demo.oauth2.auth.model.Authority;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.model.Department;
import demo.oauth2.auth.model.Organization;
import demo.oauth2.auth.repository.CredentialsRepository;
import demo.oauth2.auth.repository.DepartmentRepository;

@SpringBootApplication
@EnableResourceServer
@EnableHazelcastHttpSession
@EnableJpaAuditing
@ComponentScan
public class DemoOauth2AuthServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2AuthServerApplication.class, args);
	}
	
	
	@Autowired
	private CredentialsRepository credentialRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		
		Credentials a = new Credentials();
		
		a.setName("abc");
		a.setPassword("password");
		a.setVersion(1);
		
		Authority au = new Authority();
		
		au.setAuthority("ROLE_SSS");
		
		
		List<Authority> authority = new ArrayList<Authority>();
		
		authority.add(au);
		
		a.setAuthorities(authority);
		
		
		a = credentialRepository.save(a);
		
		
		
		Department dept = new Department();
		dept.setName("IT");
		dept.setDescription("this is description");
		
		
		Organization o = new Organization();
		o.setName("TEST ORG");
		o.setDescription("TEST ORG");
		
		dept.setOrganization(o);
		
		dept = departmentRepository.save(dept);
			
		
		
		
		a.setDepartment(dept);
		
		a = credentialRepository.save(a);
		
		
		
		Department searchDept = departmentRepository.findByName("IT");
		
		
		Credentials b = new Credentials();
		b.setName("xyz");
		b.setPassword("abcd1234");
		b.setVersion(1);
		
		b.setAuthorities(authority);
		b.setDepartment(searchDept);
		b = credentialRepository.save(b);
		
		//System.out.println(dept.getId());
		
		
		
		searchDept = departmentRepository.findByName("IT");
				
//		
//		System.out.println("Department " + searchDept.getName());
//		//= searchDept.getCredentials()
//		
//		
		for(Credentials c : searchDept.getCredentialsSet()) {
			System.out.println(c.getName());
		}
//		
//		
//		
//		//System.out.println(size);
//		
//		Credentials x = credentialRepository.findByName("xyz");
//		
//		System.out.println(x.getDepartment().getName());
		
		
				
	}

}
