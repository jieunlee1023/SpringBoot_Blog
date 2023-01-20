<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">

	<h2>블로그 글수정하기</h2>
	<br>
	<input type="hidden" id="board--id" value="${boardData.id }">
	<div class="mb-3 mt-3">
		<input type="text" id="title" class="form-control" placeholder="enter title" value="${boardData.title}">
	</div>

	<div class="mb-3">
		<textarea rows="8" id="content" class="form-control" placeholder="enter content">${boardData.content }</textarea>
	</div>

	<button type="submit" id="update--btn" class="btn btn-warning">글수정 완료</button>
</div>

<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>