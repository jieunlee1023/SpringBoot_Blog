<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form action="/auth/loginProc" method="post">
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
		<button type="submit" id="btn--login" class="btn btn-primary">signIn</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>
