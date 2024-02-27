<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책 생성하기</title>
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h2 class="display-4">책 생성하기</h2>
		</div>
	</div>
	<div class="container">
	<!-- form태그에 action을 비워두면 다시 자기자신  컨트롤러(form.do)로 돌려준다 -->	
		<form action="" method="post">
			<div class="row">
				<div class="col-md-12 md-2">
					<div class="row">
						<div class="col-md-2">
							<label for="title" class="col-form-label">제목</label>
						</div>
						<div class="col-md-10">
							<input type="text" class="form-control" name="title" id="title"/>
						</div>
					</div>
				</div>
				<div class="col-md-12 md-2">
					<div class="row">
						<div class="col-md-2">
							<label for="title" class="col-form-label">카테고리</label>
						</div>
						<div class="col-md-10">
							<input type="text" class="form-control" name="category" id="category"/>
						</div>
					</div>
				</div>
				<div class="col-md-12 md-2">
					<div class="row">
						<div class="col-md-2">
							<label for="title" class="col-form-label">가격</label>
						</div>
						<div class="col-md-10">
							<input type="text" class="form-control" name="price" id="price"/>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-warning">등록</button>
				<a href="/book/list.do" class="btn btn-primary">목록</a>
			</div>
		</form>
	</div>
</body>
</html>