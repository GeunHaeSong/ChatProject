/**
 * 
 */

$(function() {
	// 자물쇠 아이콘을 누르면 pw의 타입을 바꾸기 위한 변수
	var pw_type = 1; 	
	
	$("#btn_login").click(function() {
		console.log("클릭");
	});

	// 자물쇠 아이콘을 누르면 pw의 타입을 바꿈
	$(".input_wrap").on("click", "#pw_change", function() {
		if(pw_type == 0) {
			$("#m_pw").attr("type", "password");
			$("#pw_change").removeClass();
			$("#pw_change").addClass("fas fa-lock icon_change");
			pw_type = 1;
		} else {
			$("#m_pw").attr("type", "text");
			$("#pw_change").removeClass();
			$("#pw_change").addClass("fas fa-lock-open icon_change");
			pw_type = 0;
		}
	});
	
	// 회원가입 폼으로
	$("#go_join").click(function(e) {
		e.preventDefault();
		location.href = "/member/joinForm";
	});
});