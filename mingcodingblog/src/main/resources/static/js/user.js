
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
			url: "/api/user",
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

	login: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		};
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),
			contentType: "application/json; charset=tecoutf-8",
			dataType: "json"
		}).done(function(data) {
			if (data.status == "OK") {
				alert("로그인 성공");
				location.href = "/";
			} else {
				alert("로그인 실패!");
			}

			//alert("로그인 성공");
			//console.log(data);
			//location.href = "/";
		}).fail(function(error) {
			alert("로그인 실패!");
		});


	}
}
index.init();