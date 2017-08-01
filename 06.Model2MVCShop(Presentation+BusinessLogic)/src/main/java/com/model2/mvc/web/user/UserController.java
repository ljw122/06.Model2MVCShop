package com.model2.mvc.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.model2.mvc.service.user.UserService;

@Controller
public class UserController {

	/*Field*/
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Value("#{commonProperties['pageUnit'] ? :5}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']? : 3}")
	int pageSize;
	
	
	/*Constructor*/
	public UserController(){
		System.out.println(getClass());
	}
	
	
	
}
