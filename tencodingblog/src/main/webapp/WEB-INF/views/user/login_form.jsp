<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<br>
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<label for="username">username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username"
				value="teco">
		</div>
		<div class="form-group">
			<label for="password">password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password"
				value="asd1234">
		</div>
		<button type="submit" id="btn--login" class="btn btn-primary">signIn</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=a3af89fb8269257123c6854ffafc500d&redirect_uri=http://localhost:9090/auth/kakao/callback&response_type=code">
			<img alt="카카오로그인" src="/image/kakao_login.png" width="70" height="40"></a>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
