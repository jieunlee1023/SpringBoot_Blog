<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<style>
.board--info {
	display: flex;
	margin-top: 10px;
	justify-content: space-between;
}

.board--info span {
	color: grey;
	font-size: 13px;
	font-style: italic;
}

.board--content {
	margin: 10px;
}
</style>

<br>
<br>
<div class="container">
	<div class="board--list">
		<h2>${boardData.title }</h2>
		<div class="board--info">
			<span>${boardData.createDate }</span> <span class="board--urlcopy">URL 복사</span> <input type="text"
				value="localhost:8888/board/detail/${boardData.id }" id="urlAddress" style="display: none;">
		</div>
		<c:if test="${isWriter eq true }">
			<div>
				<a href="/board/modify/${boardData.id }" type="button" id="board--update" class="btn btn-warning">update</a>
				<button type="button" id="board--delete" class="btn btn-danger">delete</button>
				<input type="hidden" value="${boardData.id }" id="board--Id">
			</div>
		</c:if>
		<hr>
	</div>

	<div class="board--content">${boardData.content }</div>


</div>

<script type="text/javascript">
	$(document).ready(function() {
		$(".board--urlcopy").bind("click", function() {
			let urlAddress = $("#urlAddress");
			urlAddress.css('display', 'block').select();
			document.execCommand("Copy");
			urlAddress.css('display', 'none').select();
			alert("URL 주소가 복사되었습니다.");
			return false;
		});
	})
</script>
<script type="text/javascript" src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>