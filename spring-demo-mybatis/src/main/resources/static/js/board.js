let boardInit = {
	init: function() {
		$("#save--btn").bind("click", () => {
			this.save();
		});
		$("#board--delete").bind("click", () => {
			this.delete();
		});
		$("#update--btn").bind("click", () => {
			this.update();
		});
	},
	save: function() {
		let sendData = {
			title: $('#title').val(),
			content: $('#content').val()
		}

		$.ajax({
			type: 'POST',
			url: '/api/board/save',
			data: JSON.stringify(sendData),
			contentType: "application/json;charset=UTF-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			alert("글 등록 완료");
			location.href = "/board/list"
		}).fail(function(error) {
			alert("글 등록 실패");
		});

	},
	update: function() {
		let boardId = $("#board--id").val();
		let sendData = {
			id: boardId,
			title: $('#title').val(),
			content: $('#content').val()
		}

		$.ajax({
			type: 'PUT',
			url: '/api/board/update/' + boardId,
			data: JSON.stringify(sendData),
			contentType: "application/json;charset=UTF-8",
			dataType: "json"
		}).done(function(data, textStatus, xhr) {
			alert("글 수정 완료");
			console.log(data);
			location.href = "/board/list"
		}).fail(function(error) {
			console.log(error);
			alert("글 수정 실패");
		});

	},
	delete: function() {
		
		let boardId = $('#board--Id').val();
		console.log(boardId);

		$.ajax({
			type: 'DELETE',
			url: '/api/board/delete/' + boardId,
		}).done(function(data, textStatus, xhr) {
			console.log(data);
			alert("글 삭제 완료");
			//location.href = "/board/list"
		}).fail(function(error) {
			console.log(error);
			alert("글 삭제 실패");
		});

	}
}

boardInit.init();