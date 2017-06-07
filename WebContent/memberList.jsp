<%@page import="java.util.List"%>
<%@page import="com.board.dto.MemberListView"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	MemberListView viewData =
	(MemberListView)request.getAttribute("viewData");

	Integer pageNumber = (Integer)request.getAttribute("pageNumber");
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



<br />
<hr />
<br />
<div id="wrap">
	<div id="boardHeader">
		<h3>
			회원 목록
		</h3>
			<a href="join"><button>회원등록</button></a>
	</div>
	<br />
	<table>
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>패스워드</th>
			<th>구분</th>
		</tr>
		<% if(viewData.getMemberCountPerPage()>0){
			List<Member> memberList=viewData.getMemberList();
			for(int i =0; i<memberList.size();i++){
			%>
				<tr>
					<td>${viewData.firstRow+i}</td>
					<td><%=memberList.get(i).getId()%></td>
					<td><%=memberList.get(i).getPwd()%></td>
					<td><a href="deleteMember?memberId=<%=memberList.get(i).getId()%>&page=
				${pageNumber}">
								삭제&nbsp;/</a> 
				   <a href="updateMember?memberId=<%=memberList.get(i).getId()%>&page=
					${pageNumber}">
								&nbsp;수정</a></td>
				</tr>
					
			<%				
			}
		}else{ %>
				<tr>
					<td style="text-align: center;">내용이 없습니다.</td>
				</tr>
			<%} %>
	</table>

	<div id="pageNum">
		<%for(int i=1;i<viewData.getPageTotalCount()+1;i++){ %>
			<a href="memberList?page=<%=i%>">[<%=i%>]</a>
		<%}
		%>
	</div>
</div>

</body>
</html>



