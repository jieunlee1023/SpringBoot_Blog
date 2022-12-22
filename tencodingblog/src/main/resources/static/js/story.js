let index = {

	init: function() {
		$("#kakaopay").bind("click", () => {
			this.kakaopay();
		})
	},

	kakaopay: function() {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
			type:"GET",
			url: "/story/kakaopay",
		}).done(function(res) {
			if (res.status == 500) {
				alert("카카오페이결제를 실패하였습니다.")
			} else {
				alert("카카오페이결제를 시작합니다!")
				//alert(res.tid); //결제 고유 번호
				var kakaoPaymentBox = res.next_redirect_pc_url;
				location.href = kakaoPaymentBox;
			}
		}).fail(function(error) {
			alert("실패");
			console.log(error);
		});
	},
}

index.init();
