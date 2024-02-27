<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h2 class="display-4">책 상세보기</h2>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12 md-2">
				<div class="row">
					<div class="col-md-2">
						<label for="title" class="col-form-label">제목</label>
					</div>
					<!-- 
						VO가 아닌 map으로 받아오는거라서 쿼리결과로 받아오는 키 값이 대문자인지도 알아야 함.
						SQL에서는 컬럼명이 대문자 
					 -->
					<div class="col-md-10">${book.TITLE }</div>
				</div>
			</div>
			<div class="col-md-12 md-2">
				<div class="row">
					<div class="col-md-2">
						<label for="title" class="col-form-label">카테고리</label>
					</div>
					<div class="col-md-10">${book.CATEGORY }</div>
				</div>
			</div>
			<div class="col-md-12 md-2">
				<div class="row">
					<div class="col-md-2">
						<label for="title" class="col-form-label">가격</label>
					</div>
					<div class="col-md-10">${book.PRICE }</div>
				</div>
			</div>
			<div class="col-md-12 md-2">
				<div class="row">
					<div class="col-md-2">
						<label for="title" class="col-form-label">날짜</label>
					</div>
					<div class="col-md-10">${book.INSERT_DATE }</div>
				</div>
			</div>
			<a href="/book/update.do?bookId=${bookId }" class="btn btn-info">수정</a>
			<button type="button" class="btn btn-danger" id="delBtn">삭제</button>
			<a href="/book/list.do" class="btn btn-primary">목록</a>
		</div>
		<form action="/book/delete.do" method="post" id="delForm">
			<input type="hidden" name="bookId" value="${bookId }"/>
		</form>
	</div>
</body>
<script type="text/javascript">
	var delForm = $("#delForm");
	var delBtn = $("#delBtn");
	
	delBtn.on('click', function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
</script>
</html>