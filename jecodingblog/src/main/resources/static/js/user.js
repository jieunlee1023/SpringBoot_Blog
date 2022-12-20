
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--update").bind("click", () => {
			this.update();
		});
	},

	save: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");


		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},

			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data) {
			if (data.status == "OK") {
				alert("회원가입이 완료되었습니다!");
			}
		}).fail(function(error) {
			alert("회원가입 실패!");

		});
	},
	update: function() {
		
		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}

		$.ajax({
			beforeSend: function(xhr){
				xhr.setRequestHeader(csrfHeader,token);
			},
			type: "PUT",
			url: "/api/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data) {
			if (data.status == "OK") {
				alert("회원정보 수정 완료!");
			}
		}).fail(function() {
			alert("회원정보 수정 실패!");
		});

	},

}
index.init();