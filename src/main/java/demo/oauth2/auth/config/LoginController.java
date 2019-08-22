package demo.oauth2.auth.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import demo.oauth2.auth.services.CredentialService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private CredentialService credentialService;
	
	@RequestMapping("/ssoLogin")
	public String ssologin() {
		return "ssoLogin";
	}
		
	@RequestMapping("/ssoAccountLocked")
	public String ssoAccountLocked() {
		return "ssoAccountLocked";
	}
	
	
	@GetMapping("/changePassword")
	public String changePassword() {
		return "changePassword";
	}
	
	@PostMapping("/changePassword/process")
	public ModelAndView changePasswordProcess(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("newPassword2") String newPassword2
			) {						
		
		HashMap<String, Object> params = new HashMap<String, Object>();
						
		boolean invalidProcess = false;
		
		if(password.contentEquals(newPassword)) {
			params.put("msg", "old password and new password cannot be same");
			invalidProcess = true;
		}
		
		if(credentialService.verifyPassword(username, password) && !invalidProcess) {
			params.put("msg", "invalid password");
			invalidProcess = true;
		}
		
		if(credentialService.isFulfilledPasswordPolicy(newPassword) == false && !invalidProcess) {
			params.put("msg", "new password not fulfill password policy");
			invalidProcess = true;
		}
		
		if(newPassword.contentEquals(newPassword2) && !invalidProcess ) {
			params.put("msg", "new passwords are not match");
			invalidProcess = true;
		}
		
			
		if(newPassword.contentEquals(newPassword2) && !invalidProcess) {
			credentialService.updatePassword(username, newPassword);
			params.put("msg", "password has been updated. please open the application again");
		}
				
		
		return new ModelAndView("changePasswordProcess", params);
	}
	
	
	
}
