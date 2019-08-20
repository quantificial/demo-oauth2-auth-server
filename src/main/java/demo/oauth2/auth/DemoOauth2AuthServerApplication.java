package demo.oauth2.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@ComponentScan
public class DemoOauth2AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2AuthServerApplication.class, args);
	}

}
