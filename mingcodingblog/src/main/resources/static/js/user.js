
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--login").bind("click", () => {
			this.login();

		});

	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log("여기는 들어오니");
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(data) {
			console.log(data);
			if (data.status == "OK") {
				alert("회원가입 완료!");
				location.href = "/";
			}

		}).fail(function(error) {
			alert("회원가입 실패 ");
		});


	},

}
index.init();