<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.net.URLDecoder"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function funct(ang, id){
		if(ang != null){
			alert(ang);
		} else {
			alert("ㅋ");
		}
				
	}
</script>
</head>
<body>
<h1>회원</h1>



<br>
	${sessionScope.loginId}님 어서오세요 <br>
	<c:if test="${sessionScope.loginId eq 'admin'}">
		<button onclick="location.href='memberlistpaging'">회원목록</button>
	</c:if>
	<button onclick="location.href='mypage?bwriter=${sessionScope.loginId}'">마이페이지</button><br>
	<button onclick="location.href='logout'">로그아웃</button>
<br>
<h1>게시판</h1>
	<button onclick="location.href='boardwrite?bwriter?=${sessionScope.loginId}'">글쓰기</button>
	<button onclick="location.href='boardlist'">리스트</button>


</body>
</html>