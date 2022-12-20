

let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--login").bind('click', () => {
			this.login();
		});
		$("#btn--update").bind('click', () => {
			this.update();
		});

	},

	save: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");
		console.log("token 확인:" + token);
		console.log("csrfHeader 확인:" + csrfHeader);

		// form 태그에 사용자가 입력한 값을 가지고 오기 --> 자바스크립트 변수로
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);

		//  ajax 통신 구현
		//$.ajax().done().fail(); 
		$.ajax({

			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},

			//회원가입 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http 메시지 body 영역에 들어감
			contentType: "application/json; charset=utf-8", // 보낼 때 데이터 타입
			dataType: "json", //응답이 왔을 때 MINE TYPE 지정,  JSON --> javascript Object 자동 변환
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			if (data.status == "OK") {
				alert("회원가입 완료");
				location.href = "/";
			}
		}).fail(function(error) {
			//console.log(error);
			//console.log(error.responseJSON.message);
			alert("회원가입 실패 : " + error.responseJSON.message);

		});
	},


	/*	login: function() {
	
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
			};
			$.ajax({
				type: "POST",
				url: "/auth/loginProc",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(function(data, textStatus, xhr) {
				alert("로그인 성공");
				console.log(data);
				location.href = "/";
			}).fail(function(error) {
				alert("로그인 실패");
			});
		}

 */

	update: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		// 방어적 코드... (password, email 입력했는지)

		$.ajax({

			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "PUT",
			url: "/api/user",
			data: JSON.stringify(data),
			contentType: 'application/json;charset=utf-8',
			dataType: "json",
		}).done(function(data, textStatus, xhr) {
			if (data.status) {
				alert("수정이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("수정이 실패했습니다.");
		});
	}
}

index.init();