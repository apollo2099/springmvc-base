                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     package com.base.web.management;

//import javax.inject.Inject;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("management.indexController")
@RequestMapping("/management")
public class IndexController{

	@RequestMapping("")
	public String index(Model model) {

		model.addAttribute("now", new Date());
		
		return "/management/mainfream/index_index";
	}

	
}