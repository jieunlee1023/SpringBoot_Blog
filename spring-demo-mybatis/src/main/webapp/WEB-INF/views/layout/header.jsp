<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
	<div class="m--wrap">

		<nav class="navbar navbar-expand-md bg-dark navbar-dark">
			<!-- Brand -->
		_	<a class="navbar-brand" href="/">MyBlog</a>

			<!-- Toggler/collapsibe Button -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Navbar links -->
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/board/list">Home</a></li>
					<c:choose>
					<c:when test="${sessionScope.principal eq null }">
						<li class="nav-item"><a class="nav-link" href="/user/signin-form">Sign in</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/signup-form">Sign up</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/board/write-form">Write</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/logout">Logout</a></li>
					</c:otherwise>
					</c:choose>
					
				</ul>
			</div>
		</nav>