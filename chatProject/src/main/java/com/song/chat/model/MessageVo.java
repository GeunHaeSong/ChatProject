package com.song.chat.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MessageVo {
	private int message_num;
	private String message;
	private Timestamp time;
	private String m_id;
	private int room_num;
}
