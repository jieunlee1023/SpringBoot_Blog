<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br> <br>
	<button class="btn btn-secondary" onclick="history.back();">돌아가기</button>

	<c:if test="${board.user.id == principal.user.id}">
		<a class="btn btn-warning" href="/board/${board.id}/update-form">수정하기</a>
		<button id="btn--delete" class="btn btn-danger">삭제하기</button>
	</c:if>
	<br> <br>

	<div>
		<input type="hidden" id="board-id" value="${board.id}"> 글 번호 :
		<span id=""> <i>${board.id + 100}</i></span>
	</div>
	<div>
		작성자 : <span id=""> <i>${board.user.username}</i></span>
	</div>
	<br> <br>

	<div class="">
		<h3>${board.title}</h3>
	</div>

	<div>${board.content}</div>
	<br> <br>

	<div class="card">

		<div class="card-body">
			<textarea rows="1" class="form-control" id="content" disabled></textarea>
		</div>
		<div class="card-footer">
			<button class="btn btn-secondary d-flex" id="btn-reply-save" disabled>등록</button>
		</div>
	</div>
	<br>

	<div class="card">
		<div class="card-header">댓글 목록</div>
	</div>
	<div>
	<ul class="list-group  d-flex mr-2">
		<c:forEach var="reply" items="${board.replys}">
			<li class="list-group-item d-flex justify-content-between">
				
					<c:choose>
						<c:when test="${replyData.id == reply.id}">
							<textarea rows="1" class="form-control  d-flex mr-2"
								id="reply-content">${reply.content}</textarea>
						</c:when>
						<c:otherwise>
							<div>${reply.content }</div>
						</c:otherwise>
					</c:choose>
				

				<div class=" d-flex">
					<button class="btn btn-warning badge btn-sm d-flex mr-2"
						onclick="index.replyUpdate(${board.id},${reply.id});">저장</button>
					<button class="btn btn-danger badge btn-sm d-flex mr-2"
						onclick="index.replyDelete(${board.id},${reply.id});" disabled>삭제</button>
						<div>작성자 : &nbsp; ${reply.user.username} &nbsp; &nbsp; &nbsp;</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>

</div>

<script type="text/javascript" src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>