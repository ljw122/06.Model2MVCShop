<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<title>열어본 상품 보기</title>

</head>
<body>
	당신이 열어본 상품을 알고 있다
<br>
<br>

<c:forTokens var="i" items="${cookie.history.value }" delims=",">
	<a href="getProduct.do?prodNo=${i}&menu=search" target="rightFrame">${i}</a><br/>
</c:forTokens>

</body>
</html>