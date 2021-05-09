package com.song.chat.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.song.chat.model.MemberVo;

@Repository
@Mapper
public interface MemberMapper {
	int idDupCheck(@Param("m_id") String m_id);
	void insertMember(MemberVo memberVo);
	int loginCheck(@Param("m_id") String m_id, @Param("m_pw") String m_pw);
	void loginStateCheck(@Param("m_id") String m_id);
}
