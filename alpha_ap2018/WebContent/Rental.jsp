<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>貸出画面</title>
</head>
<body>
	<center><h1>貸		出</h1></center>

	<hr size="2" align="center" />
		<form action="RentalServlet" method="post">
			<center>識別ラベル　<input type="text" name="rabelId" value="" /><br></br>
			<p>ユーザーID　<input type="text" name="userId" value="" /></p></center>
			<div align="center"> <input type="submit" value="貸出" /> </div>
		</form>

	<hr size="2" align="center" />
		<c:if test="${not empty requestScope.message}">	<%--messageが空でなけば表示 --%>
			<c:out value="${message}" />

		</c:if>
		<p><a href="Admin.jsp">メニュー画面へ戻る</a></p>

</body>
</html>