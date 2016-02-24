package com.base.web;

import java.util.Map;

import javax.annotation.Resource;

//import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.web.user.User;
import com.base.web.user.UserService;
import com.base.web.user.UserServiceMgr;
import com.base.web.user.persistence.beans.SysUser;

@Controller
public class IndexController {

	@Resource
	private UserServiceMgr userServiceMgr;
	@Resource
	private UserService userService;
	
	@RequestMapping("")
	public String index() {
		System.out.println("Test");
		

		return "/index";
	}
	
	@RequestMapping("/login")
	public String login() {
//		User user= userServiceMgr.getUserByUsername("admin");
//		System.out.println(user.getSysUser().getUserAge());
//		System.out.println(user.getSysUser().getUserName());

		SysUser user2=userService.findByUsername("admin");
		System.out.println(user2.getUserAge());
		System.out.println(user2.getUserName());
		return "/login";
	}
	
	
}