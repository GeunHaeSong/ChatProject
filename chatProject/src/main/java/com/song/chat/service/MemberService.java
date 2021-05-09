package com.song.chat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.chat.mapper.MemberMapper;
import com.song.chat.model.MemberVo;

@Service
public class MemberService {
	@Autowired
	public MemberMapper mapper;
	
	public int idDupCheck(String m_id) {
		return mapper.idDupCheck(m_id);
	}
	
	public void insertMember(MemberVo memberVo) {
		mapper.insertMember(memberVo);
	}
	
	public int loginCheck(String m_id, String m_pw) {
		return mapper.loginCheck(m_id, m_pw);
	}
	
	public void loginStateCheck(String m_id) {
		mapper.loginStateCheck(m_id);
	}
}
