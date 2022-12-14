<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<c:forEach var="board" items="${boards}">

	<div class="card m-2">
		<div class="card-body">
			<h4>${board.title}</h4>
			<p>${board.content}</p>
			<a href="#" class="btn btn-primary">⛄상세보기</a>
		</div>
	</div>
	<br>
</c:forEach>



<%@ include file="layout/footer.jsp"%>