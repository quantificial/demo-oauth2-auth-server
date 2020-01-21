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
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableResourceServer
@EnableHazelcastHttpSession
@EnableJpaAuditing
@ComponentScan
@Slf4j
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
		
		a = credentialRepository.save(a);
		
		log.info(a.getId() + " " + a.getName());
		a.setName("xyz");
		
		a = credentialRepository.save(a);
		
		log.info(a.getId() + " " + a.getName());
				
	}

}
