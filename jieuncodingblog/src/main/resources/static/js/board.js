let index = {

	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--delete").bind("click", () => {
			this.delete();
		});
		$("#btn--update").bind("click", () => {
			this.update();
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
	},

	delete: function() {
		let id = $('#board-id').text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id
		}).done(function(data) {
			if (data.status == "OK") {
				alert("글 삭제가 완료 되었습니다!");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("글 삭제에 실패했습니다!");
		});

	},

	update: function() {

		let boardId = $("#board-id").attr("data-id");
		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		}

		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(data) {
			if (data.status == "OK") {
				alert("수정이 완료되었습니다!");
				location.href = "/";
			}
		}).fail(function(error) {
			alert("수정에 실패했습니다!");
		});
	}
}



index.init();