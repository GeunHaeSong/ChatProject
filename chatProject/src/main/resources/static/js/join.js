/**
 * 
 */

$(function() {
	
	var name_result = false;
	var id_result = false;
	var pw_result = false;
	var pw2_result = false;
	var check_result = false;
	
	// 생년월일 만들기
	dateMaker();
	
	// 이름 체크
	$("#m_name").blur(function() {
		var parent_div = $(this).parent();
		parent_div.find(".dup_result").remove();
		var result = "";
		
		var m_name = $(this).val();
		var name_rgx = /^[가-힣a-zA-Z]{1,15}$/;
		
		if(name_rgx.test(m_name)) {
			result = "success";
			name_result = true;
		} else {
			result = "fail";
			name_result = false;
		}
		resultMaker(result, parent_div);
	});
	
	// 아이디 체크
	$("#m_id").blur(function() {
		var parent_div = $(this).parent();
		parent_div.find(".dup_result").remove();
		var m_id = $(this).val();
		
		var id_rgx = /^[a-z0-9]{5,15}$/;
		if(!id_rgx.test(m_id)) {
			resultMaker("fail", parent_div);
			id_result = false;
			return;
		}
		
		// 같은 아이디가 존재하는지 에이잭스 체크
		var url = "/login/idDupCheck";
		var sendDate = {
			"m_id" : m_id	
		};
		$.get(url, sendDate, function(rData) {
			if(rData == "success") {
				resultMaker("success", parent_div);
				id_result = true;
			} else {
				resultMaker("fail", parent_div);
				id_result = false;
			}
		});
	});
	
	// 비밀번호 체크
	$("#m_pw").blur(function() {
		var parent_div = $(this).parent();
		parent_div.find(".dup_result").remove();
		var result = "";
		
		var m_pw = $(this).val();
		var pw_rgx = /^[a-z0-9]{7,15}$/;
		
		if(pw_rgx.test(m_pw)) {
			result = "success";
			pw_result = true;
		} else {
			result = "fail";
			pw_result = false;
		}
		resultMaker(result, parent_div);
	});
	
	// 재입력 비밀번호 체크
	$("#m_pw2").blur(function() {
		var parent_div = $(this).parent();
		parent_div.find(".dup_result").remove();
		var result = "";
		
		var m_pw2 = $(this).val();
		var pw_rgx = /^[a-z0-9]{7,15}$/;
		if(!pw_rgx.test(m_pw2)) {
			resultMaker("fail", parent_div);
			pw2_result = false;
			return;
		}
		
		var m_pw = $("#m_pw").val();
		if(m_pw == m_pw2) {
			result = "success";
			pw2_result = true;
		} else {
			result = "fail";
			pw2_result = false;
		}
		resultMaker(result, parent_div);
	});
	
	// sms
	$("#sms_send").click(function() {
		var phone = $("#m_phone").val();
		var phone_rgx  = /^\d{3}\d{3,4}\d{4}$/;
		if(phone_rgx.test(phone)) {
			var url = "/login/smsSend";
			var sendDate = {
					"phone" : phone
			};
			$.get(url, sendDate, function(rData) {
				if(rData == "success") {
					alert("성공적으로 인증번호를 보내셨습니다.");
				}
			});
		} else {
			alert("잘못된 번호 형식입니다.");
		}
	});
	
	// 인증번호 체크
	$("#sms_check").blur(function() {
		var parent_div = $(this).parent();
		parent_div.find(".dup_result").remove();
		
		var sms_num = $(this).val();
		if(sms_num.length < 1) {
			resultMaker("fail", parent_div);
			check_result = false;
		}
		
		var url = "/login/smsCheck";
		var sendDate = {
				"sms_num" : sms_num
		};
		
		$.get(url, sendDate, function(rData) {
			if(rData == "success") {
				resultMaker(rData, parent_div);
				check_result = true;
			} else {
				check_result = false;
			}
		});
	});
	
	// 등록 버튼
	$(".btn_login").click(function() {
//		if(name_result == true && id_result == true && pw_result == true
//			&& pw2_result == true && check_result == true) {
//			joinForm.submit();
//		}
		// 테스트 용도(문자보내기 꺼둠)
		if(name_result == true && id_result == true && pw_result == true
				&& pw2_result == true) {
			var yy = $("#yy_box option:selected").val();
			var mm = $("#mm_box option:selected").val();
			var dd = $("#dd_box option:selected").val();
			var birthday = yy + mm + dd;
			$("#m_birthday").val(birthday);
			joinForm.submit();
		}
	});
	
	// 취소 버튼(로그인 페이지로 이동시키기)
	$("#btn_cancle").click(function() {
		location.href = "/#";
	});
});

// 결과를 사용자가 확인할 수 있게 표시해주기(마우스 올려두면 툴팁으로 잘못된 점 표시)
function resultMaker(result, variable_location) {
	var html = "<i class='dup_result help-tip ";
	var message = "잘못된 형식 입니다.\n";
	message += "이름은 한글, 소문자, 대문자로 최소 1글자부터 15글자까지 허용합니다.\n";
	message += "아이디와 비밀번호는 영어 소문자, 숫자 0~9로 최소 5글자부터 15글자까지 허용합니다.\n";
	message += "전화번호는 01012345678 형식으로 작성해주셔야합니다.\n";
	message += "인증번호는 문자로 확인하여 주세요.";
	
	if(result == "success") {
		html += "fas fa-check-circle' style='color: green; ";
	} else {
		html += "far fa-times-circle' title='" + message + "' style='color: red; ";
	}
	html += "font-size:20px; position: absolute; right: 5%; top: 18%;'></i>";
	
	$(variable_location).append(html);
}

// 생년월일 만들기
function dateMaker() {
	var date = new Date();
	var now_yy = date.getFullYear();
	
	for(var i = (now_yy - 130); i <= now_yy; i++) {
		dateHtmlMaker(i, "년", "#yy_box");
	}
	for(var i = 1; i <= 12; i++) {
		dateHtmlMaker(i, "월", "#mm_box");
	}
	for(var i = 1; i <= 31; i++) {
		dateHtmlMaker(i, "일", "#dd_box");
	}
}

function dateHtmlMaker(i, str, variable_location) {
	var date_value = i; 
	if(i < 10) {
		date_value = "0" + i;
	}
	var html = "<option value=" + date_value + ">" + i + str + "</option";
	$(variable_location).append(html);
}
