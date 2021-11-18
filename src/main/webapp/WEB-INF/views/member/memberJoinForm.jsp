<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
function CheckEmail(str){
	var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
	if(!reg_email.test(str)){
		return false;
	} else {
		return true;
	}
}
function idCheck(){
	var id = $("#id").val();
	if(!CheckEmail(id)){
		alert("email을 입력하세요.");
		$("#id").val("");
		return;
	}
	
	$.ajax({
		url : "ajaxIdCheck.do",
		type : "post",
		data : {chkid : id},
		dataType : "text",
		success : function(data) {
			if(data == '1'){
				alert("사용할 수 있는 아이디 입니다.");
				$("#idchk").val("Yes");
				$("#password").focus();
			} else {
				alert(id+"는 이미 존재하는 아이디 입니다.");
				$("#id").val("");
				$("#id").focus();
			}
		}
	});

}
function formCheck(){
	if($("#idchk").val() != 'Yes'){
		alert("아이디 중복체크를 해주세요.");
		return false;
	}
	if($("#password").val() != $("#passwordChk").val()){
		alert("패스워드가 일치 하지 않습니다.");
		$("#password").val("");
		$("#passwordChk").val("");
		$("#password").focus();
		return false;
	}
	
	$("#frm").submit();
}
</script>
<style>
th {
	background-color: black;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="../home/header.jsp" />
	<div align="center">
		<div></div>
		<div>
			<h2>회원가입</h2>
		</div>
		<div>
			<form id="frm" action="memberJoin.do" method="post">
				<div>
					<table border="1">
						<tr>
							<th width="150">* ID</th>
							<td width="300"><input type="email" id="id" name="id" required="required" placeholder="email을 입력하세요"
								size="20"> <button type="button" id="idchk" onclick="idCheck()" value="No">ID중복확인</td>
						</tr>
						<tr>
							<th width="150">* Password</th>
							<td width=150"><input type="password" id="password" 
								name="password" required="required" placeholder="패스워드를 입력하세요"></td>
						</tr>
						<tr>
							<th width="150">* Password 확인</th>
							<td width=150"><input type="password" id="passwordChk"
								name="passwordChk" required="required" placeholder="패스워드를 다시 입력하세요"></td>
						</tr>
						<tr>
							<th width="150">* 이 름</th>
							<td width=150"><input type="text" id="name" name="name" required="required" placeholder="이름을 입력하세요"></td>
						</tr>
						<tr>
							<th width="150">전화번호</th>
							<td width=150"><input type="text" id="tel" name="tel" required="required" placeholder="연락처를 입력하세요"></td>
						</tr>
						<tr>
							<th width="150">주 소</th>
							<td width=150"><input type="text" id="address"
								name="address" size="50" required="required" placeholder="주소를 입력하세요"></td>
						</tr>
					</table>
				</div>
				<br> <input type="button" onclick="formCheck()" value="회원가입">&nbsp;&nbsp;&nbsp; 
				<input type="reset"	value="취 소">&nbsp;&nbsp;&nbsp;
			</form>
		</div>

	</div>

</body>
</html>