<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">

	<h2>블로그 글쓰기</h2>
	<br>
	<div class="mb-3 mt-3">
		<input type="text" id="title" class="form-control" placeholder="enter title">
	</div>

	<div class="mb-3">
		<textarea rows="8" id="content" class="form-control" placeholder="enter content"></textarea>
	</div>

	<button type="submit" id="save--btn" class="btn btn-warning">글쓰기 완료</button>
</div>

<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>