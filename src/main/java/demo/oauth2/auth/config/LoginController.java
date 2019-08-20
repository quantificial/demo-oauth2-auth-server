package demo.oauth2.auth.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/ssoLogin")
	public String ssologin() {
		return "ssoLogin";
	}
		
	@RequestMapping("/ssoAccountLocked")
	public String ssoAccountLocked() {
		return "ssoAccountLocked";
	}
	
}
