<? xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>���Ђ̒ǉ�</title>
</head>
<body>
	<div style="padding:50px;">
	<form action="BookManagement" method="post">
		<table border="1" summary="���Џ��">
			<tr>
				<td>���ʃ��x��</td>
				<td><input type="text" name="label" value=""/></td>
				<td>�^�C�g��</td>
				<td><input type="text" name="title" value=""/></td>
			</tr>
			<tr>
				<td>���</td>
				<td><input type="text" name="author" value=""/></td>
				<td>�o�Ŏ�</td>
				<td><input type="text" name="publisher" value=""/></td>
			</tr>
			<tr>
				<td>�o�ŔN</td>
				<td><input type="text" name="publicationYear" value=""/></td>
				<td>�݌ɐ�</td>
				<td><input type="text" name="stockNum" value=""/></td>
			</tr>
			<tr>
				<td>�Ȗږ�</td>
				<td><input type="text" name="subjectName" value="" /></td>
				<td>�摜�p�X</td>
				<td><input type="text" name="imagePath" value=""/></td>
			</tr>
			<tr>
				<td colspan="4"/>
			</tr>
			<tr>
				<td colspan="4">
					<button type="reset">���Z�b�g</button>
					�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@<input type="submit" name="button" value="�ǉ�" />
				</td>
			</tr>
		</table>
	</form>
	</div>
	<a href="/LibrarySystem/BookSearch">�߂�</a>
</body>
</html>