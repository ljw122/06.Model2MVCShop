<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form name="commentProduct" method="post" action="addProductComment.do">
<input type="hidden" name="prodNo" value="${product.prodNo}"/>
<input type="hidden" name="userId" value="${user.userId }"/>
<hr/>
상품평 보기


<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<c:if test="${!empty user }">
	<tr>
		<td width="104" class="ct_write">
			상품평 쓰기
		</td>
		<td width="1"></td>
		<td class="ct_write">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="80%">
						<input type="text" name="cmt" id="cmd" width="100%" />
					</td>
					<td width="20%">
						<input type="submit" value="등록"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	</c:if>
	
	${replyList.size() }
	<c:if test="${replyList.size()>0 }">
		<c:forEach var="reply" items="${ replyList }">
		<tr>
			<td width="104" class="ct_write">
				${reply.userId }
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="105">${reply.cmt }</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>	
		</c:forEach>
	</c:if>
	
</table>

</form>

