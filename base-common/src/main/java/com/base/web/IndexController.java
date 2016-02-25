package com.base.web;

import java.util.Map;

import javax.annotation.Resource;

//import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("")
	public String index() {
		System.out.println("Test");
		

		return "/index";
	}
	

	
	
}