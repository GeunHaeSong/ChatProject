package com.song.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.song.chat.model.MemberVo;

@Repository
@Mapper
public interface MemberMapper {
	List<MemberVo> getMemberList();
}
