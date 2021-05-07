package com.song.chat.util;

import java.util.HashMap;

import net.nurigo.java_sdk.api.Message;

public class SmsSendUtil {

	public static int sendSms(String phone) {
		
		try {
			int random_num = (int)(Math.random() * 59999) + 100000;
	        String api_key = "NCSFOAPJJDDPUTIM";
	        String api_secret = "OLGDHHAXHO6FXMIUXGGBERGKBOIMH6JM";
	        Message coolsms = new Message(api_key, api_secret);
	        HashMap<String, String> params = new HashMap<String, String>();
	
	        params.put("to", "01054682491");
	        params.put("from", phone);
	        params.put("type", "SMS");
	        params.put("text", "[chatProject]인증번호 [" + random_num + "]를 입력해주세요.");
	        params.put("app_version", "test app 1.2");
	
	        coolsms.send(params);
	        
	        return random_num;
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return 0;
    }
}
