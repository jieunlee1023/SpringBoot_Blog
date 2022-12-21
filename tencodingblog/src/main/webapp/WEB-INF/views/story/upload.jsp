<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/story/image/upload" method="post" enctype="multipart/form-data" class="m-5" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<div class="custom-file">
			<input type="file" name="file" class="custom-file-input" id="customFile" required="required"> 
			<label class="custom-file-label" for="customFile">업로드 할 파일을 선택해주세요</label>
			
			<div class="input-group mt-3">
				<div class="input-group-prepend">
					<span class="input-group-text">스토리 설명</span>
				</div>
				<input type="text" name="storyText" class="form-control">
				<div class="input-group mt-3">
					<button type="submit" class="btn btn-primary ">사진 업로드하기</button>
				</div>
			</div>
		</div>
	</form>

</div>
<br>
<br>

<script>
$(".custom-file-input").bind("change", function() {
	console.log("test code:"+$(this).val());
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>

<%@ include file="../layout/footer.jsp"%>