
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
		$("#btn-reply-save").bind("click", () => {
			;
			this.replySave();
		});
	},
	save: function() {


		let xcheckTitle = XSSCheck($("#title").val());

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let data = {
			title: xcheckTitle,
			content: $("#content").val()
		};


		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
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
			alert("저장실패!");
		});


	},

	deleteById: function() {
		let id = $('#board-id').val();

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
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

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let data = {
			title: $('#title').val(),
			content: $('#content').val(),
		}

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
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

	},
	replySave: function() {
		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let replyData = {
			boardId: $("#board-id").val(), //fk (board PK)
			content: $("#reply--content").val(),

		};

		$.ajax({
			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "POST",
			url: `/api/board/${replyData.boardId}/reply`,
			data: JSON.stringify(replyData),
			contentType: 'application/json;charset=utf-8',
			dataType: "json",
		}).done(function(data) {
			if (data.status == "OK") {
				alert("댓글 작성이 완료되었습니다!");
				//location.href = `/board/${replyData.boardId}`;
				console.log(data.body);
				addReplyAlement(data.body);
			}
		}).fail((error) => {
			alert("댓글 작성에 실패하였습니다.");

		});
	},
	replyDelete: function(boardId, replyId) {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$.ajax({

			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},

			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(resData) {
			if (resData.status == "OK") {
				alert("댓글 삭제 성공!");
				location.href = `/board/${boardId}`
			}
		}).fail(function(error) {
			alert("댓글 삭제 실패!");
		});
	},

	replyUpdate: function(boardId, replyId) {

		let token = $("meta[name='_csrf']").attr("content");
		let csrfHeader = $("meta[name='_csrf_header']").attr("content");

		let data = {
			content: $('#reply-content').val(),
		}

		$.ajax({

			beforeSend: function(xhr) {
				xhr.setRequestHeader(csrfHeader, token);
			},
			type: "PUT",
			url: `/api/board/${boardId}/reply/${replyId}`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(data) {
			if (data.status == "OK") {
				alert("수정이 완료되었습니다!");
				location.href = `/board/${boardId}`
			}
		}).fail(function(error) {
			alert("수정에 실패했습니다!");
		});
	},
}

function addReplyAlement(reply) {
	let childElement = `<li class="list-group-item d-flex justify-content-between" id="reply--${reply.id}">
				<div>${reply.content}</div>
				<div class=" d-flex">
						<a
							class="btn btn-warning badge d-flex mr-2 justify-content-center"
							href="/board/${reply.board.id}/reply/${reply.id}/update-reply-form">수정</a>
						<button
							class="btn btn-danger badge d-flex mr-3 justify-content-center"
							onclick="index.replyDelete(${reply.board.id},${reply.id});">삭제</button>
					<div>작성자 : &nbsp; ${reply.user.username} &nbsp; &nbsp;&nbsp;</div>
				</div>
			</li>`;

	$("#reply--box").prepend(childElement);
	$("#reply--content").val("");
}


function XSSCheck(str, level) {
	if (level == undefined || level == 0) {
		str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g, "");
	} else if (level != undefined && level == 1) {
		str = str.replace(/\</g, "&lt;");
		str = str.replace(/\>/g, "&gt;");
	}
	return str;
}

index.init();