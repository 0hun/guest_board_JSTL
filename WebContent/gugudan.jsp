<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<table border="1">
	<c:forEach var="i" begin="2" end="10">
		<tr>
			<td>
		<c:forEach var="j" begin="1" end="10">
			${i} * ${j} = ${i*j}<br/>
		</c:forEach>
			</td>
		</tr>
			
	</c:forEach>
</table>
</body>
</html>