<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
}

.notification {
  background-color: #555;
  color: white;
  text-decoration: none;
  padding: 15px 26px;
  position: relative;
  display: inline-block;
  border-radius: 2px;
}

.notification:hover {
  background: red;
}

.notification .badge {
  position: absolute;
  top: -10px;
  right: -10px;
  padding: 5px 10px;
  border-radius: 50%;
  background-color: red;
  color: white;
}
</style>
</head>
<body>
${alarmCount}
<%
	String amid = request.getParameter("amid");
%>
<script>
		playAlert = setInterval(function idOverlap() {
			var inputId = "<%=amid%>";
			console.log(inputId);
			// ajax(Asynchronous javascript and XML)
			// JSON(JavaScript Object Notation)
				$.ajax({
					type : "post",
					url : "alarmCount",
					data : {
						"amid" : inputId
					},
					dataType : "text",
					success : function(result) {
							eqMsg.innerHTML = result;
		
					},
					error : function() {
						alert("ajax 실패");
					}
				})}, 1000);
</script>
<a href="alarmlist?amid=${sessionScope.loginId}" class="notification">
  <span>알림</span>
  <span class="badge" id="eqMsg">${alarmCount}</span>
</a>

</body>
</html>