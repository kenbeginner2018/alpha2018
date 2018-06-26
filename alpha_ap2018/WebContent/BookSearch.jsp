<? xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>蔵書検索</title>
</head>
<body>

	<form action="BookManagement" method="get"><input type="submit" name="button" value="書籍の追加" />
	<div style="width:85%;aligh:center;">
	<h1>蔵書検索</h1>
	<div style="text-align:center;border:#d0d0d0 outset 5px;width:500px;height:100px;padding:10px;aligh:center;">
	<form action="BookSearch" method="post" >
		<div>
		タイトル:<input type="text" name="title" value="${requestScope.title}" />
		作者:<input type="text" name="author" value="${requestScope.author}" />
		<br/>
		</div>
		<div>
		出版社:<input type="text" name="publisher" value="${requestScope.publisher}" />
		科目:<input type="text" name="subject" value="${requestScope.subject}" />
		<br/>
		</div>
		<div>
		<input type="submit" name="button" value="検索" style="width:70px;height:20px;"/>
		<input type="submit" name="button" value="RESET" style="width:70px;height:20px;"/>
		</div>
	</form>
	</div>
	</div>
	<c:choose>
		<c:when test="${requestScope.bookList != null}">
 		<div style="width:80%;aligh:center;">
		<h2>検索結果</h2>
		<table border="1" summary="検索結果" >
			<tr>
				<th>タイトル</th>
				<th>作者</th>
				<th></th>
			</tr>
			<c:forEach var="book" items="${requestScope.bookList}">
				<tr>
					<td><c:out value="${book.title}"/></td>
					<td><c:out value="${book.author}"/></td>
					<td>
						<form action="BookManagement" method="get" >
							<input type="hidden" name="label" value="${book.label}" />
							<input type="submit" name="button" value="詳細" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</c:when>
	<c:otherwise>
		<p>該当する本はありません</p>
	</c:otherwise>
	</c:choose>

	<a href="/LibrarySystem/admin">戻る</a>
</body>
</html>