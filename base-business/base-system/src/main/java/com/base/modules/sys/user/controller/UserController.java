package com.base.modules.sys.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.modules.sys.user.service.UserService;

@Controller
public class UserController {
    @Resource
	private UserService  userService;
    
	@RequestMapping("/toLogin")
	public String login() {
		String username="admin";
		Map map= null;
		try {
			map = userService.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(map.get("userName"));
		return "/login";
	}
	
	@RequestMapping("/list")
	public String list() {
//		String username="admin";
//		 Map map=userService.findByUsername(username);
//		 System.out.println(map.get("userName"));
		return "/user/list";
	}
    
}
