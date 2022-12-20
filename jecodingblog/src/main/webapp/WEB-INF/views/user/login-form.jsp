<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form action="/auth/loginProc" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="form-group">
			<label for="username">username:</label> 
			<input type="text" 
			class="form-control" placeholder="Enter username"
			 id="username" name="username" value="jieun">
		</div>
		<div class="form-group">
			<label for="password">password:</label> 
			<input type="password"
			 class="form-control" placeholder="Enter password" 
			id="password" name="password" value="asd1234">
		</div>
		<button type="submit" id="btn--login" class="btn btn-primary mr-3">signIn</button>
	
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=af497c2db8733a72a5c02d510aa5362a&redirect_uri=http://localhost:7777/auth/kakao/callback&response_type=code">
		<img alt="카카오" src="/image/kakao_login.png" width="70" height="40"></a>
		
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
