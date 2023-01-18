<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<br>
<br>
<div class="container">

	<div></div>


	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글 제목</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boardItem" items="${boardList}">
			<c:set var="i" value="${i + 1 }"></c:set>
				<tr>
					<td> <c:out value="${i}" /> </td>
					<td><a href="/board/detail/${boardItem.id}">${boardItem.title }</a></td>
					<td>${boardItem.username }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>

	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class="page-item"><a class="page-link">Prev</a></li>
			<li class="page-item"><a class="page-link">1</a></li>
			<li class="page-item"><a class="page-link">Next</a></li>
		</ul>
	</div>

</div>

<%@include file="../layout/footer.jsp"%>