<%@ page contentType="text/html; charset=utf-8" %>
<%@ page errorPage="errorView.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>방명록 메시지 삭제함</title>
</head>
<body>
<c:choose>
	<c:when test="${not invalidPassowrd}">
		<script>
			alert('메시지를 삭제하였습니다.');
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert('입력한 암호가 올바르지 않습니다. 암호를 확인해주세요.');
		</script>
	</c:otherwise>
</c:choose>


<br/>
<a href="messageList?page=${param.page}">[목록 보기]</a>
</body>
</html>




