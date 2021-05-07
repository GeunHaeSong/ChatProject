package com.song.chat.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberVo {
	private String m_id;
	private String m_pw;
	private String m_phone;
	private String m_name;
	private Date m_birthday;
	private String m_image;
	private String m_login_state;
}
