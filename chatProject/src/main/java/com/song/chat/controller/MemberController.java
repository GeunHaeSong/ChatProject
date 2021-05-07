package com.song.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.song.chat.service.MemberService;
import com.song.chat.util.SmsSendUtil;

@Controller()
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	// sms 인증번호(서버 상에서 처리하기 위함)
	private int sms_check_num = 0;
	
	// 회원가입 화면
	@RequestMapping(value="/joinForm", method=RequestMethod.GET)
	public String joinForm() throws Exception {
		return "view/member/joinForm";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/idDupCheck", method=RequestMethod.GET)
	public String idDupCheck(String m_id) throws Exception {
		int result = memberService.idDupCheck(m_id);
		if(result == 0) {
			return "success";
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/smsSend", method=RequestMethod.GET)
	public String smsSend(String phone) {
		try {
			sms_check_num = SmsSendUtil.sendSms(phone);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/smsCheck", method=RequestMethod.GET)
	public String smsCheck(int sms_num) throws Exception {
		if(sms_check_num == sms_num) {
			return "success";
		}
		return "fail";
	}
}
