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
		let data = {
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		}

		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(data) {
			if (data.status == "OK") {
				alert("회원가입 완료!!");
				location.href = "/"
			}
		}).fail(function(error) {
			alert("회원가입 실패!!");
		});
	},
	update: function() {
		let data = {
			id: $('#id').val(),
			username: $('#username').val(),
			password: $('#password').val(),
			email: $('#email').val()
		};

		$.ajax({
			type: "PUT",
			url: "/api/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(data) {
			if (data.status == "OK") {
				alert("정보 수정을 완료했습니다.");
			}
		}).fail(function(error) {
			alert("수정에 실패했습니다.")
		});
	},


}

index.init();