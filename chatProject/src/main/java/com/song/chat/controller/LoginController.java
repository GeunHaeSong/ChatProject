package com.song.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.song.chat.model.MemberVo;
import com.song.chat.service.MemberService;
import com.song.chat.util.SmsSendUtil;

@Controller()
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	PasswordEncoder passEncoder;
	// sms 인증번호(서버 상에서 처리하기 위함)
	private int sms_check_num = 0;
	
	// 로그인 화면
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String mainView() throws Exception {
		
		return "view/loginView/loginForm";
	}
	
	// 회원가입 화면
	@RequestMapping(value="/joinForm", method=RequestMethod.GET)
	public String joinForm() throws Exception {
		return "view/loginView/joinForm";
	}
	
	// 회원가입 등록
	@RequestMapping(value="/joinRun", method=RequestMethod.POST)
	public String joinRun(RedirectAttributes rttr, MemberVo memberVo) throws Exception {
		// 비밀번호 암호화
		String m_pw = memberVo.getM_pw();
		String encode_pw = passEncoder.encode(m_pw);
		memberVo.setM_pw(encode_pw);
		
		// 회원가입 insert
		memberService.insertMember(memberVo);
		
		rttr.addFlashAttribute("join_result", "success");
		return "redirect:/login/loginForm";
	}
	
	@RequestMapping(value="/loginRun", method=RequestMethod.POST)
	public String loginRun(String m_id, String m_pw, HttpSession session, RedirectAttributes rttr) throws Exception {
		int result = memberService.loginCheck(m_id, m_pw);
		if(result == 1) {
			session.setAttribute("m_id", m_id);
			memberService.loginStateCheck(m_id);
			return "redirect:/main/home";
		}
		rttr.addFlashAttribute("login_result", "fail");
		return "redirect:/login/loginForm";
	}
	
	
	// ---------- ajax ----------------
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/idDupCheck", method=RequestMethod.GET)
	public String idDupCheck(String m_id) throws Exception {
		int result = memberService.idDupCheck(m_id);
		if(result == 0) {
			return "success";
		}
		return "fail";
	}
	
	// 인증 문자 전송시키기
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
	
	// 인증 문자 서버 상에서 체크시키기
	@ResponseBody
	@RequestMapping(value="/smsCheck", method=RequestMethod.GET)
	public String smsCheck(int sms_num) throws Exception {
		if(sms_check_num == sms_num) {
			return "success";
		}
		return "fail";
	}
}
