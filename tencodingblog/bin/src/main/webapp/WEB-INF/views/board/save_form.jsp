<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>


<div class="container">

	<form action="">

		<div class="form-group">
			<label for="title">Title</label> <input type="text" name="title" id="title" class="form-control">
		</div>


		<div class="form-group">
			<label for="content">content</label>
			<textarea name="content" id="content" rows="5" class="form-control content"></textarea>
		</div>
		<script>
			$('.content').summernote({
				placeholder : '내용을 입력해주세요!',
				tabsize : 2,
				height : 300
			});
		</script>

	</form>
	<button type="button" id="btn--save" class="btn btn-outline-secondary ">글쓰기 완료</button>
</div>

<script type="text/javascript" src="/js/board.js"></script>


<%@ include file="../layout/footer.jsp"%>