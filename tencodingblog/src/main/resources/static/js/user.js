

let index= {
	init: function(){
		$("#btn--save").bind("click", ()=>{
			this.save();
		});	
	},
	
	save: function(){
		// form 태그에 사용자가 입력한 값을 가지고 오기 --> 자바스크립트 변수로
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);
		//todo --> ajax로 통신(data -> json 변환, 자바 서버로 전송)
	}
}

index.init();