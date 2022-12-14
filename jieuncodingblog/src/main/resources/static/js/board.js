let index = {

	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset = utf-8",
			dataType: "json"
		}).done(function(data) {
			if (data.status == "OK") {
				alert("글이 저장되었습니다!");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글 저장을 실패하였습니다.");
		});
	}

}

index.init();