package com.song.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.song.chat.model.MemberVo;
import com.song.chat.service.MemberService;

@Controller()
public class HomeController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String mainView(Model model) throws Exception {
		
		List<MemberVo> memberList = memberService.getMemberList();
		model.addAttribute("memberList", memberList);
		return "mainView/home";
	}
}
