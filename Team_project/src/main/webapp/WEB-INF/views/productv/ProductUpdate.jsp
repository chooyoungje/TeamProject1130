<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
function productUpdateProcess(){
	productupdateprocessname.submit();
}
function init(){
	value = '${list.pcategory}'
	$('#pcategory').val(value).prop("selected", true);	
}

</script>
</head>
<body onload = "init()">

카테고리 : ${list.pcategory}
<form action="productupdateprocess" method="post" name ="productupdateprocessname" enctype="multipart/form-data">
	상품 번호 <input type = "text" id = "pnumber" name = "pnumber" value = "${list.pnumber}" readonly><br>
	분류 <select id = "pcategory" name ="pcategory" class = "pcategory" data-pcategory = "Drink">
                    <option value="Snack">과자류</option>
                    <option value="Drink">음료류</option>
					<option value="Icecream">아이스크림류</option>
					<option value="Noodle">면류</option>
					<option value="Food">식사류</option>
                </select><br>
         상품 이름  <input type="text" id = "pname" name = "pname" value ="${list.pname}"/><br>
         상품 가격  <input type="text" id="pprice" name="pprice" value = "${list.pprice}"><br>
         상품 갯수 <input type="text" id = "pcount" name ="pcount" value = "${list.pcount}"><br>   
         <img src="${pageContext.request.contextPath}/resources/uploadfile/${list.pimage}" style = "width : 50px; hegith : 50px"alt="사진">
         상품 사진 <input type="file" id="pfile" name="pfile"> <br>     
	상품 소개 <textarea id = "pinfo" name = "pinfo" rows = "5">${list.pinfo}</textarea><br>
	</form>

	<button onclick = "productUpdateProcess()">수정</button>
</body>
</html>