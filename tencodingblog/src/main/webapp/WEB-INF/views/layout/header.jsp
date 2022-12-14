<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta id="_csrf" name="${_csrf.parameterName}" content="${_csrf.token}">
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}">
<title>Blog</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<link rel="shortcut icon" type="image/x-icon" href="http://sanriokorea.co.kr/favicon.png.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${empty principal}">
						<li class="nav-item"><a class="nav-link" href="/auth/login_form">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/auth/join_form">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/board/save_form">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/update_form">회원정보</a></li>
						<li class="nav-item dropdown">
							<a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown"> STORY </a>
								<ul class="dropdown-menu" role="menu">
										<li class="nav-item" >
											<a href="/story/home" class="dropdown-item" >
												<span class="text-secondary">
													<i class="fas fa-cloud"></i>스토리 홈
												</span>
											</a>	
										</li>
										
										<li class="nav-item" >
											<a href="/story/upload" class="dropdown-item" >
												<span class="text-secondary">
													<i class="fas fa-cloud"></i> 업로드
												</span>
											</a>	
										</li>
								</ul>
						</li>
						<li class="nav-item"><a class="nav-link" href="/m-logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>