<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- root에서 실행 --%>
<%-- 유저화면 / 관리자화면 두개로 분리 --%>

<c:set var="menu" value="${!empty param.menu ? param.menu : \"search\" }"/>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="css/admin.css" type="text/css">
<script type="text/javascript">
	function fncGetList(currentPage){
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
	function fncOrderList(orderCondition, orderOption){
		document.getElementById("orderCondition").value=orderCondition;
		document.getElementById("orderOption").value=orderOption;
		fncGetList('1');
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="listProduct.do?menu=${menu }" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						${menu=='manage' ? "상품관리" : "상품 목록조회" }
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>		
		<td align="left">
			<c:if test="${menu=='search' }">
				<input type="checkbox" name="stockView" onchange="javascript:fncGetList('1')" ${search.stockView ? "checked=\"checked\"" : "" }
						class="ct_input_g" height="19"/>완판 상품 보기
			</c:if>
		</td>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px" onchange="javascript:fncGetList('${resultPage.currentPage }')">
				<option value="1" ${!empty search.searchCondition && search.searchCondition==1 ? "selected" : ""} >상품명</option>
				<option value="2" ${!empty search.searchCondition && search.searchCondition==2 ? "selected" : ""} >상품가격</option>
			</select>
			<c:choose>
				<c:when test="${search.searchCondition==2 }">
					<input type="text" name="searchKeyword" value="${!empty search.searchKeyword? search.searchKeyword : "" }"
							class="ct_input_g" style="width:65px; height:19px" />&nbsp;이상&nbsp;
					<input type="text" name="searchKeyword2" value="${!empty search.searchKeyword2? search.searchKeyword2 : "" }"
							class="ct_input_g" style="width:65px; height:19px" />&nbsp;이하
				</c:when>
				<c:otherwise>
					<input type="text" name="searchKeyword" value="${!empty search.searchKeyword? search.searchKeyword : "" }"
							class="ct_input_g" style="width:200px; height:19px" />
				</c:otherwise>
			</c:choose>
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="70">No</td>
		<td class="ct_line02">
			<input type="hidden" id="orderCondition" name="orderCondition" value="${!empty search.orderCondition? search.orderCondition : "" }"/>
			<input type="hidden" id="orderOption" name="orderOption" value="${!empty search.orderOption? search.orderOption : "" }"/>
		</td>
		<td class="ct_list_b" width="150">상품명&nbsp;
			<a href="javascript:fncOrderList('1', '${search.orderCondition=='1' && search.orderOption=='ASC' ? 'DESC' : 'ASC' }')">
				${!empty search.orderCondition && search.orderCondition=='1' ? (search.orderOption=='ASC'? "▲":"▼") : "-" }
			</a>
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">가격&nbsp;
			<a href="javascript:fncOrderList('2','${search.orderCondition=='2' && search.orderOption=='ASC' ? 'DESC' : 'ASC' }')">
				${!empty search.orderCondition && search.orderCondition=='2' ? (search.orderOption=='ASC'? "▲":"▼") : "-" }
			</a>
		</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">${menu=='manage' ? "등록일":"제품 상세 정보"}</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">${menu=='manage' ? "남은수량":"현재상태"}</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
		

	<!-- 여기서 for문 돌려 pageSize 수로 -->
	<c:set var="i" value="${resultPage.totalCount- (resultPage.currentPage-1)*resultPage.pageSize + 1 }"/>
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${i-1}"/>
		<tr class="ct_list_pop">
			<td align="center">${i }</td>
			<td></td>
					
			<td align="left">
				<c:choose>
					<c:when test="${empty product.proTranCode || menu=='manage' }">
						<a href="getProduct.do?prodNo=${product.prodNo }&menu=${menu}">${product.prodName}</a>
					</c:when>
					<c:otherwise>
						${product.prodName }
					</c:otherwise>
				</c:choose>
				
			</td>
			
			<td></td>
			<td align="left">${product.price}</td>
			<td></td>
			<td align="left">${menu=='manage'? product.regDate : product.prodDetail }</td>
			<td></td>
			<td align="left">
			<c:choose>
				<c:when test="${menu=='manage' }">
					${product.stock }
				</c:when>
				<c:otherwise>
					${product.stock==0 ? "재고없음" : "판매중" }
				</c:otherwise>
			</c:choose>
			</td>	
		</tr>
		<tr>
			<td colspan="11" bgcolor="D6D7D6" height="1"></td>
		</tr>	
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			<input type="hidden" id="currentPage" name="currentPage" value="1"/>
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!-- 페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>