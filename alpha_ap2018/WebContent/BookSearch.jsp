<? xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>��������</title>
</head>
<body>

	<form action="BookManagement" method="get"><input type="submit" name="button" value="���Ђ̒ǉ�"/></form>
	<h1>��������</h1>
	<form action="BookSearch" method="post" >
		�^�C�g��:<input type="text" name="title" value="${requestScope.title}" />
		���:<input type="text" name="author" value="${requestScope.author}" />
		�o�Ŏ�:<input type="text" name="publisher" value="${requestScope.publisher}" />
		�Ȗ�:<input type="text" name="subject" value="${requestScope.subject}" />
		<input type="submit" name="button" value="����" />
		<input type="submit" name="button" value="RESET" />
	</form>
	<c:choose>
		<c:when test="${requestScope.bookList != null}">
		<h2>��������</h2>
		<table border="1" summary="��������" >
			<tr>
				<th>�^�C�g��</th>
				<th>���</th>
				<th></th>
			</tr>
			<c:forEach var="book" items="${requestScope.bookList}">
				<tr>
					<td><c:out value="${book.title}"/></td>
					<td><c:out value="${book.author}"/></td>
					<td>
						<form action="BookManagement" method="get" >
							<input type="hidden" name="label" value="${book.label}" />
							<input type="submit" name="button" value="�ڍ�" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<p>�Y������{�͂���܂���</p>
	</c:otherwise>
	</c:choose>
	<a href="/LibrarySystem/BookSearch">�߂�(������)</a>
</body>
</html>