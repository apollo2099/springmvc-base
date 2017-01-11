package com.base.web.index;

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