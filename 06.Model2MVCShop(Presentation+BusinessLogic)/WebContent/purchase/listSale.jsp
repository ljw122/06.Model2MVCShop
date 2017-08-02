<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- root에서 실행 --%>

<html>
<head>
<title>판매 목록조회</title>

<link rel="stylesheet" href="css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetList(currentPage){
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="listSale.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">판매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">
			<input type="hidden" name="userId" value="${purchase.buyer.userId }"/>
			전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지
		</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="70">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="180">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">구매자 ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">배송 희망일</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>


	<c:set var="i" value="${resultPage.totalCount- (resultPage.currentPage-1)*resultPage.pageSize + 1}"/>
	<c:forEach var="purchase" items="${list}">	
		<c:set var="i" value="${i-1}"/>
		<tr class="ct_list_pop">
			<td align="center">
				<a href="getPurchase.do?tranNo=${purchase.tranNo}">${i}</a>
			</td>
			<td></td>
			<td align="left">
				<a href="getProduct.do?prodNo=${purchase.purchaseProd.prodNo}&menu=manage">${purchase.purchaseProd.prodName} (수량 : ${purchase.purchaseCount })</a>
			</td>
			<td></td>
			<td align="left">
				<a href="getUser.do?userId=${purchase.buyer.userId}">${purchase.buyer.userId}</a>
			</td>
			<td></td>
			<td align="left">
				${purchase.dlvyDate}
			</td>
			<td></td>
			<td align="left">
				<c:choose>
					<c:when test="${purchase.tranCode=='1' }">
						구매완료
					</c:when>
					<c:when test="${purchase.tranCode=='2' }">
						배송중
					</c:when>
					<c:when test="${purchase.tranCode=='3' }">
						배송완료
					</c:when>
				</c:choose>
			</td>
			<td></td>
			<td align="left">
				<c:if test="${purchase.tranCode=='1'}">
					<a href="updateTranCode.do?tranNo=${purchase.tranNo}&tranCode=2&menu=manage">배송하기</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>
	</c:forEach>
	

</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<jsp:include page="../common/pageNavigator.jsp"/>
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>