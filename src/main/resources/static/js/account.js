let index = {
		
	init: function() {
		
		$("#btn-save").on("click", () => { // function(){} 대신 ()=>를 쓴 이유는 this를 바인딩하기 위한 값
			this.account();
		});
		
	},
	account: function() {
		
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/auth/account",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // MIME body type
			dataType: "json" // response type
		}).done(function(response) {

			alert("회원가입 완료");
			location.href = "/";

		}).fail(function(error) {

			alert(JSON.stringify(error));

		});
	},
}

index.init();