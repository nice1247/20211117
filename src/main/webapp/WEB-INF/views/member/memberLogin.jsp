<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../home/header.jsp"/>
<div align = "center">
	<div><h1>${message }</h1></div>
	<div>
		<c:if test="${author == 'admin'}">
			<a href="memberList.do">멤버목록 보기</a>
		</c:if>
	</div>
</div>

</body>
</html>