package com.song.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainViewController {
	
	// 메인 화면
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homeForm() throws Exception {
		return "view/mainView/home";
	}
}
