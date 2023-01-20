<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">

	<h2>Login</h2>
	<p>다양한 기록들을 남기고 공유 해주세요!💞</p>

	<br>
	<form action="/user/signin-proc" method="post">
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="enter username" name="username" value="${username }">
		</div>

		<div class="mb-3">
			<input type="password" class="form-control" placeholder="enter password" name="password" value="asd123">
		</div>

		<c:if test="${isNotSignin eq true }">
			<p class="alert alert-danger">잘못된 요청입니다.</p>
		</c:if>
		<div class="form-check mb-3">
			<label class="form-check-label"> 
				<input id="remember" type="checkbox" class="form-check-input" name="remember"> 아이디 저장
			</label>
		</div>
		<button type="submit" class="btn btn-warning">로그인</button>
	</form>


</div>

<%@include file="../layout/footer.jsp"%>