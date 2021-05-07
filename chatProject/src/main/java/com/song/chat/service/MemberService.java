package com.song.chat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.chat.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	public MemberMapper mapper;
	
	public int idDupCheck(String m_id) {
		return mapper.idDupCheck(m_id);
	}
}
