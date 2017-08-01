<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<c:if test="${resultPage.beginUnitPage!=1 }">
	<a href="javascript:fncGetList('${resultPage.beginUnitPage-1 }')">◀ 이전페이지</a>
</c:if>
<c:if test="${resultPage.beginUnitPage==1 }">
	◀ 이전페이지
</c:if>

<c:forEach var="i" begin="${resultPage.beginUnitPage}" end="${resultPage.endUnitPage}">
	<a href="javascript:fncGetList('${i}')">${i}</a>
</c:forEach>

<c:if test="${resultPage.endUnitPage!=resultPage.maxPage}">
	<a href="javascript:fncGetList('${resultPage.endUnitPage+1}')">다음페이지 ▶</a>
</c:if>
<c:if test="${resultPage.endUnitPage==resultPage.maxPage}">
	다음페이지 ▶
</c:if>