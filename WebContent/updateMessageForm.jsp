<%@page import="com.board.dto.Message"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form action="updateMessage" method="post">
		<input type="hidden" name="messageId" value="${message.id}" />
		<input type="hidden" name="page" value="${param.page}%>" />
		이름: <input type="text" name="guestName"
			value="${message.guestName}" /> <br /> 암호: <input
			type="password" name="password" value="${message.password}" /> <br />
		메시지:
		<textarea name="message" cols="30" rows="3">
			${message.message}</textarea>
		<br /> <input type="submit" value="수정" />
	</form>
	<hr>

</body>
</html>





