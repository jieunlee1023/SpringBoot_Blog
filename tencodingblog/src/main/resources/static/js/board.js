
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
	},
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: 'application/json;charset=utf-8',
			dataType: "json",
		}).done(function(data) {
			console.log(data);
			if (data.status == "OK") {
				alert("저장완료!");
				location.href="/";
			}
		}).fail((error) => {
			alert(error.responseJSON.error);
		});
	}

}

index.init();