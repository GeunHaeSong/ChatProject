package com.song.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.chat.mapper.MemberMapper;
import com.song.chat.model.MemberVo;

@Service
public class MemberService {
	@Autowired
	public MemberMapper mapper;
	
	public List<MemberVo> getMemberList() {
		return mapper.getMemberList();
	}
}
