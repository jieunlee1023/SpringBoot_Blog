<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>



<div class="container">
	<form>
		<input type="hidden" name="id" id="id" value="${principal.user.id}">
		<h3>회원정보 수정하기♥</h3>
		<br>
		<div class="form-group">
			<label for="username">username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username"
				value="${principal.user.username}" readonly="readonly">
		</div>

		<c:if test="${empty principal.user.oauth}">

			<div class="form-group">
				<label for="password">password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" value="">
			</div>

		</c:if>

		<c:choose>
			<c:when test="${empty principal.user.oauth}">
				<label for="email">email:</label>
				<input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}">
			</c:when>
			<c:otherwise>
				<input type="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}" readonly="readonly">
			</c:otherwise>
		</c:choose>

	</form>
			<br>
	<c:if test="${empty principal.user.oauth}">
		<button type="button" id="btn--update" class="btn btn-primary">회원정보수정</button>
	</c:if>
</div>

<script type="text/javascript" src="/js/user.js"></script>


<%@ include file="../layout/footer.jsp"%>