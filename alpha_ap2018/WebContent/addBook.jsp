<? xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>書籍の追加</title>
</head>
<body>
	<div style="padding:50px;">
	<!-- エラーメッセージを出力する -->
	<c:if test="${requestScope.notNullError != null}"><p><c:out value="${requestScope.notNullError}" /></p></c:if>
	<c:if test="${requestScope.pubYearError != null}"><p><c:out value="${requestScope.pubYearError}" /></p></c:if>
	<c:if test="${requestScope.stockNumError != null}"><p><c:out value="${requestScope.stockNumError}" /></p></c:if>
	<form action="BookManagement" method="post">
		<table border="1" summary="書籍情報">
			<tr>
				<td>識別ラベル</td>
				<td><input type="text" name="label" value=""/></td>
				<td>タイトル</td>
				<td><input type="text" name="title" value=""/></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" value=""/></td>
				<td>出版社</td>
				<td><input type="text" name="publisher" value=""/></td>
			</tr>
			<tr>
				<td>出版年</td>
				<td><input type="text" name="publicationYear" value=""/></td>
				<td>在庫数</td>
				<td><input type="text" name="stockNum" value=""/></td>
			</tr>
			<tr>
				<td>科目名</td>
				<td><input type="text" name="subjectName" value="" /></td>
				<td>画像パス</td>
				<td><input type="text" name="imagePath" value=""/></td>
			</tr>
			<tr>
				<td colspan="4"/>
			</tr>
			<tr>
				<td colspan="4">
					<button type="reset">リセット</button>
					　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　<input type="submit" name="button" value="追加" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	<a href="BookSearch">戻る</a>
</body>
</html>