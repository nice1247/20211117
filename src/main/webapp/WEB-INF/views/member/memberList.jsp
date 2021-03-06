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
	function authorEdit(id, cnt) {
		var id = id;
		var author = $("#author" + cnt).val();
		console.log(id + " " + author);

		$.ajax({
			url : "ajaxAuthorUpdate.do",	// 호출명
			type : "post", 					// 전송방식 get, post
			data : {
				id : id,
				author : author
			}, 								// 전달된 데이터 K:V
			dataType : "text",				// 처리된 결과를 받을 타입 html, json, xml
			success : function(data) { 		// data에 리턴값이 담겨있다.
				if (data == 'yes') {
					alert(id + "님의 권한이 변경되었습니다.")
				} else {
					alert(id + "님의 권한변경이 실패했습니다.")
				}
			}

		});

	}
</script>
<style>
th {
	background-color: black;
	color: white;
}

td {
	text-align: center;
}

tr:hover {
	background-color: #fcecae;
	color: violet;
}
</style>
</head>
<body>
	<jsp:include page="../home/header.jsp" />
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

				<c:forEach items="${members }" var="member" varStatus="status">
					<tr>
						<td>${member.id }</td>
						<td>${member.name }</td>
						<td>${member.tel }</td>
						<td>${member.address }</td>
						<td><select id="author${status.count}" name="author">
								<option value="admin"
									<c:if test="${member.author eq 'admin'}">selected</c:if>>ADMIN</option>
								<option value="user"
									<c:if test="${member.author eq 'user'}">selected</c:if>>USER</option>
						</select></td>
						<td><button type="button"
								onclick="authorEdit('${member.id}', '${status.count}')">변경</button></td>
					</tr>
				</c:forEach>

			</table>
		</div>
		<br> <input type="button" value="홈으로"
			onclick="location.href='home.do'">
	</div>


</body>
</html>