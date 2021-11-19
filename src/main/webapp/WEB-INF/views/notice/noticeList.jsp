<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	th {
		background-color : black;
		color : white;
	}
	td {
	text-align : center;
	}
</style>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align = "center">
	<div><h1>공지사항 리스트</h1></div>
	<div>
		<table border="1">
			<tr>
				<th width="70">No</th>
				<th width="100">작성자</th>
				<th width="200">제 목</th>
				<th width="100">작성일자</th>
				<th width="200">첨부파일</th>
			</tr>
			<c:forEach items="${notices}" var = "notice">
				<tr>
					<td>${notice.no }</td>
					<td>${notice.name }</td>
					<td>${notice.title }</td>
					<td>${notice.wdate }</td>
					<td>${notice.fileName }</td>
				</tr>
			</c:forEach>
		</table>
	</div><br>
	<div>
		<c:if test="${not empty id }">
			<button type="button" onclick="location.href='noticeForm.do'">글쓰기</button>
		</c:if>
	</div>
</div>

</body>
</html>