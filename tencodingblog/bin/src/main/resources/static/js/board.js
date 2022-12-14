
let index = {
	init: function() {
		$("#btn--save").bind("click", () => {
			this.save();
		});
		$("#btn--delete").bind("click", () => {
			this.deleteById();
		});
		$("#btn--update").bind("click", () => {
			this.update();
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
				location.href = "/";
			}
		}).fail((error) => {
			alert(error.responseJSON.error);
		});
	},

	deleteById: function() {
		let id = $('#board-id').text();


		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id
		}).done(function(data, textStatus, xhr) {
			if (data.status == "OK") {
				alert("글 삭제가 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alret("글 삭제에 실패하였습니다.");
		});
	},

	update: function() {

		//HTML 태그에 직접 속성을 정할 수 있다. data - *
		// data-*  값을 가지고 오기 위해서 JQuery --> (선택자).attr("data-*");
		let boardId = $("#board-id").attr("data-id");

		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		}

		$.ajax({
			type: "PUT",
			url: "/api/board/" + boardId,
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