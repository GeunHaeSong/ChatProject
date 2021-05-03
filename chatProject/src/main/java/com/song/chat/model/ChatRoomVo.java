package com.song.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ChatRoomVo {
	private int room_num;
	private String room_state;
	private int room_count;
	private String m_id;
	private String m_type;
}
