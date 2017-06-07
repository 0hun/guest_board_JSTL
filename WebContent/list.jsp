<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.board.dto.Message"%>
<%@page import="java.util.List"%>
<%@page import="com.board.dto.MessageListView"%>
<%@page import="com.board.service.GetMessageListService"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MessageListView viewData = (MessageListView) request
			.getAttribute("viewData");
%>

<style>
* {
	margin: 0;
	padding: 0;
}

table {
	width: 100%;
	background: black;
}

td, th {
	background: white;
	text-align: center;
	padding: 0.2em;
}

div#wrap>table td:nth-child(1) {
	width: 15%;
}

div#wrap>table td:nth-child(2) {
	width: 15%;
}

div#wrap>table td:nth-child(3) {
	width: 50%;
}

div#wrap {
	width: 80%;
	min-width: 500px;
	max-width: 850px;
	margin: 0 auto;
}

div#pageNum {
	padding: 10px;
	text-align: center;
}

div#boardHeader>h3 {
	text-align: center;
	color: green;
}

div#boardHeader {
	overflow: hidden;
}

div#boardHeader button {
	float: right;
}
</style>


<%-- <%
		if (viewData.isEmpty()) {
	%> --%>

<%-- <%
		} else { /* 메시지 있는 경우 처리 시작 */
	%> --%>
<br />
<hr />
<br />
<div id="wrap">
	<div id="boardHeader">
		<h3>방명록 게시판</h3>
		<a href="writeMessage"><button>글쓰기</button></a>
	</div>
	<br>

	<table>
		<tr>
			<th>메시지 번호</th>
			<th>손님 이름</th>
			<th>메시지</th>
			<th>구분</th>
		</tr>
		<c:choose>
			<c:when test="${viewData.messageTotalCount>0}">
				<c:forEach var="msg" items="${viewData.messageList}">
					<tr>
						<td>${msg.id}</td>
						<td>${msg.guestName}</td>
						<td>${msg.message}</td>
						<td><a
							href="deleteMessage?messageId=${msg.id}&page=
				${pageNumber}">
								삭제&nbsp;/ </a><a
							href="updateMessage?messageId=${msg.id}&page=
				${pageNumber}">
								&nbsp;수정</a></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
			<td colspan="4" style="text-align: center;">내용이 없습니다.</td>
		</tr>
	</table>

	<div id="pageNum">
	<c:forEach var ="i" begin="1" end="${viewData.pageTotalCount}">
		<a href="messageList?page=${i}">[${i}]	</a>
		
	
	</c:forEach>

		

		<%-- 	<%
				} /* 메시지 있는 경우 처리 끝 */
			%>
 --%>
	</div>
</div>

</body>
</html>



