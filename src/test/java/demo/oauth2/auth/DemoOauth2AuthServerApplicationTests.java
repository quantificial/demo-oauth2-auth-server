package demo.oauth2.auth;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoOauth2AuthServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoOauth2AuthServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
