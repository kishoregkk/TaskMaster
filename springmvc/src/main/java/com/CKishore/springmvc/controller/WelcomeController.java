package com.CKishore.springmvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@SessionAttributes("nam")
public class WelcomeController {
	
	
	@RequestMapping("/")
	public String gotoWelcomePage(ModelMap model)
	{
		model.put("nam",findLoggedInUserName());
		return "welcome";
	}
	
	public String findLoggedInUserName() {
		Authentication authentication= SecurityContextHolder.getContext()
									.getAuthentication();
		return authentication.getName();
	}
	

}
