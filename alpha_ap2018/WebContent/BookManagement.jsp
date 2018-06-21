<? xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>書籍情報</title>
</head>
<body>

	<div style="width:200px;height:250px;background:#ffcccc;
border:#ff0000 solid 1px;float:left;">
	<img src="${bookData.imagePath}" width="200" height="250" alt="書影"/>
	</div>
	<div style="padding:50px;">
	<form action="BookManagement" method="post">
		<table border="1" summary="書籍情報">
			<tr>
				<td>識別ラベル</td>
				<td>${bookData.label}</td>
				<td>タイトル</td>
				<td><input type="text" name="title" value="${bookData.title}"/></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input type="text" name="author" value="${bookData.author}"/></td>
				<td>出版社</td>
				<td><input type="text" name="publisher" value="${bookData.publisher}"/></td>
			</tr>
			<tr>
				<td>出版年</td>
				<td><input type="text" name="publicationYear" value="${bookData.publicationYear}"/></td>
				<td>在庫数</td>
				<td><input type="text" name="stockNum" value="${bookData.stockNum}"/></td>
			</tr>
			<tr>
				<td>科目名</td>
				<td><input type="text" name="subjectName" value="${subName}" /></td>
				<td>画像パス</td>
				<td><input type="text" name="imagePath" value="${bookData.imagePath}"/></td>
			</tr>
			<tr>
				<td colspan="4"/>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" name="label" value="${bookData.label}" />
					<button type="submit" name="button" value="詳細">RESET</button>
					　　　<input type="submit" name="button" value="更新" />
					　　　　　　　　　　　　　　　　　　　　　　　<input type="submit" name="button" value="削除"/>
				</td>
			</tr>
		</table>
	</form>
	</div>
	<a href="/LibrarySystem/BookSearch">戻る</a>
</body>
</html>