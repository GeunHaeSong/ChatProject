package com.song.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
public class HomeController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mainView() throws Exception {
		
		return "view/loginView/loginForm";
	}
}
