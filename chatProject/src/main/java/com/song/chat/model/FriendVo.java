package com.song.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class FriendVo {
	private int friend_num;
	private String m_id;
	private String friend_id;
	private String friend_state;
}
