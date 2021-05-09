package com.song.chat.util;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionBind implements HttpSessionBindingListener {
	
	public SessionBind() {
		
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("바인딩 해제됌");
		HttpSessionBindingListener.super.valueUnbound(event);
	}
}
