package demo.oauth2.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import demo.oauth2.auth.model.Authority;
import demo.oauth2.auth.model.Credentials;
import demo.oauth2.auth.repository.CredentialRepository;

@SpringBootApplication
@EnableResourceServer
@EnableJpaAuditing
@ComponentScan
public class DemoOauth2AuthServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2AuthServerApplication.class, args);
	}
	
	
	@Autowired
	private CredentialRepository credentialRepository;

	@Override
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
		
		
		credentialRepository.save(a);
				
	}

}
