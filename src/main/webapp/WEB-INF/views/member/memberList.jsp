<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function authorEdit(str){
		var id = str;
		//ajax로 처리해야하는 부분
		//권한값을 어떻게 찾을지 고민????
	}
</script>
<style>
	th {
	background-color: black;
	color: white;
	}
	td {
	text-align : center;
	}
	tr:hover {
	background-color: #fcecae;
	color: violet;
	}
</style>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
	<div align="center">
		<div>
			<h1>멤 버 목 록</h1>
		</div>
		<div>
		<table border="1">
			<tr>
				<th width="200">아이디</th>
				<th width="150">이 름</th>
				<th width="200">전화번호</th>
				<th width="400">주 소</th>
				<th width="100">권 한</th>
				<th width="100">권한수정</th>
			</tr>

			<c:forEach items="${members }" var="member">
				<tr>
					<td>${member.id }</td>
					<td>${member.name }</td>
					<td>${member.tel }</td>
					<td>${member.address }</td>
					<td><select id="author" name="author">
						<c:if test="${member.author eq 'admin'}">
							<option value="admin" selected="selected">ADMIN</option>
							<option value="user">USER</option>
						</c:if>
						<c:if test="${member.author eq 'user'}">
							<option value="admin">ADMIN</option>
							<option value="user" selected="selected">USER</option>
						</c:if>
						</select>
					</td>
					<td><button type="button" onclick="authorEdit('${member.id}')">변경</button></td>
				</tr>
			</c:forEach>

		</table>
		</div><br>
		<input type="button" value="홈으로" onclick="location.href='home.do'">
	</div>
	

</body>
</html>