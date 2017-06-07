<%@page import="com.board.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
	div#headWrap{
		position:relative;
		width: 80%;
		height:100px;
		min-width: 450px;
		max-width: 850px;
		margin: 0 auto;
		
		overflow:hidden;
	}
	div#topMenu{
		float:right;
	}
	div#adminMenu{
		position:absolute;
		left:0;
		bottom:0;
	}	
</style>
</head>
<body>
	<c:if test="${empty sessionScope.loginUser} ">
		<script>
			alert('로그인하세요!');
			location.href="login";
		</script>
	</c:if>


<div id="headWrap">
	<div id="topMenu">
		<span>${sessionScope.loginUser.id}님 반갑습니다.</span>
		<a href="logout"><button>로그아웃</button></a>
	</div>
	<c:if test="${sessionScope.loginUser.id eq 'admin'}">
		<div id="adminMenu">
			<a href="memberList"><button>회원관리</button></a>
			<a href="messageList"><button>방명록관리</button></a>
			<a href="#"><button>상품관리</button></a>
		</div>	
	</c:if>	
</div>















