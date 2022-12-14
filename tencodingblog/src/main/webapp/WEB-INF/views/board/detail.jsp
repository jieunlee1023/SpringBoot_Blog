<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-secondary" onclick="history.back();">돌아가기</button>
	<button id="btn--update" class="btn btn-warning ">수정하기</button>
	<button id="btn--delete" class="btn btn-danger ">삭제하기</button>
	<br> <br> <br>

	<div>
		글 번호 : <span id="board-id"> <i>${board.id} </i>
		</span>
	</div>
	<div>
		글 작성자 : <span id=""> <i>${board.user.username} </i>
		</span>
	</div>
	<br> <br>


	<div class="">
		<h3>${board.title}</h3>
	</div>

	<div>${board.content}</div>

	<br> <br> <br>
</div>

<script type="text/javascript" src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>