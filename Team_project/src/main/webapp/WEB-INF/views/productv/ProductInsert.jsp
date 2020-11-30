<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function productAdd(){
	productinsertname.submit();
}
</script>
</head>
<body>
<h2>ProductInsert.jsp</h2>
<form action="productinsert" method="post" name ="productinsertname" enctype="multipart/form-data">
			분류 <select id = "pcategory" name ="pcategory">
                    <option value="Snack">과자류</option>
                    <option value="Drink">음료류</option>
					<option value="Icecream">아이스크림류</option>
					<option value="Noodle">면류</option>
					<option value="Food">식사류</option>
                </select><br>
         상품 이름  <input type="text" id = "pname" name = "pname"/><br>
         상품가격  <input type="text" id="pprice" name="pprice"><br>
         상품갯수 <input type="text" id = "pcount" name ="pcount"><br>   
         상품사진 <input type="file" id="pfile" name="pfile" > <br>     
	상품소개 <textarea id = "pinfo" name = "pinfo" rows = "5"></textarea><br>
	</form>
	<button onclick = "productAdd()">추가</button>
	
</body>
</html>