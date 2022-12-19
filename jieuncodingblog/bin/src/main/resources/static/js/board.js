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
		$("#btn-reply-save").bind("click", () => {
			console.log("여기들어오니");
			this.replySave();

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
	},
	replySave: function() {
		let replyData = {
			boardId: $("#board-id").val(),
			content: $("#content").val()
		}
		$.ajax({
			type: "POST",
			url: `/api/board/${replyData.boardId}/reply`,
			data: JSON.stringify(replyData),
			contentType: 'application/json;charset=utf-8',
			dataType: "json",
		}).done(function(data) {
			if (data.status == "OK") {
				alert("댓글 작성이 완료되었습니다!");
				location.href = `/board/${replyData.boardId}`;
			}
		}).fail((error) => {
			alert("댓글 작성에 실패하였습니다.");

		});
	},

	replyDelete: function(boardId, replyId) {

		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(resData) {
			if (resData.status == "OK") {
				alert("댓글 삭제 완료");
				location.href = `/board/${boardId}`
			}
		}).fail(function(error) {
			alert("댓글 삭제 실패!");
		});

	},



}



index.init();