<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-write" id="add_mt">
		<colgroup>
			<col style="width: 120px" />
			<col style="width: *" />
		</colgroup>
		<tr>
			<th>날짜</th>
			<td>
				<div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
					data-link-format="yyyy-MM-dd">
					<input class="form-control" size="16" type="text" id="mt_wdate" name="mt_wdate" value="${mt_wdate}" style="text-align: left;"> <span
						class="input-group-addon" style="padding: 0 10px; border-top: 1px solid #ccc; border-right: 1px solid #ccc; border-bottom: 1px solid #ccc;">
						<span class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" class="form-control" id=mt_subject name="mt_subject" placeholder="제목" value="${mt_subject}" required></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<div id="summernote">${mt_content}</div>
			</td>
		</tr>
		<tr>
			<th>파일첨부</th>
			<td>
				<div id="my_file_upload" class="dropzone">
					<div class="fallback">
						<input name="file" type="file" multiple />
					</div>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>