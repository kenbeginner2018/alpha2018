<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>返却画面</title>
</head>
<body>
	<h1>返却・検索</h1>

	<hr size="2" align="center" />
		<form action="ReturnServlet" method="get">
			識別ラベル<input type="text" name="labelId" value="" />
			<br></br>
			ユーザーID<input type="text" name="userId" value="" />
			<br></br>
			<input type="submit" value="検索" />
		</form>

	<h1>貸出一覧</h1>

	<hr size="2" align="center" />
		<c:if test="${not empty requestScope.message}">	<%--messageが空でなけば表示 --%>
			<c:out value="${message}" />
			<p><a href="ReturnServlet">貸出一覧へ戻る</a></p>
		</c:if>
		<p><a href="Admin.jsp">メニュー画面へ戻る</a></p>

	<c:if test="${requestScope.RentalList != null}"><%--返却フラグの判定 --%>
		<table border="1">
			<tr>
				<th>識別ラベル</th>
				<th>ユーザーID</th>
				<th>氏名</th>
				<th>返却予定日</th>
				<th></th>
			</tr>
			<c:forEach var="rtn" items="${requestScope.RentalList}">
				<c:if test="${rtn.returnFlag == false}">
					<tr>
						<td><c:out value="${rtn.label}" /> </td>
						<td><c:out value="${rtn.userId}" /> </td>
						<td><c:out value="${Changer.userIdToName(rtn.userId)}" /> </td>
						<c:choose>
							<c:when test="${rtn.extendFlag == true}">		<%--延長フラグの判定--%>
								<td><c:out value="${Changer.rentalDateToExtendDate(rtn.rentalDate)}" /> </td>
							</c:when>
							<c:otherwise>
								<td><c:out value="${rtn.rentalDate}" /> </td>
							</c:otherwise>
						</c:choose>
						<td>
							<form action="ReturnServlet" method="post">
								<input type="submit" name="btn" value="返却" />
								<input type="hidden" name="userId" value="${rtn.userId}" />
								<input type="hidden" name="rentalId" value="${rtn.rentalId}" />
								<input type="hidden" name="labelId" value="${rtn.label}" />
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>