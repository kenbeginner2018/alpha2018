<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>大学の図書管理システム</title>
</head>
<body>
	<a href="userlist">ユーザ一覧へ戻る</a>
	<h1>ユーザ追加</h1>
	<hr />
	<form action="useradd" method="post">
		<!-- エラーメッセージを出力する -->
		<c:if test="${requestScope.notNullError != null}"><p><c:out value="${requestScope.notNullError}" /></p></c:if>
		<c:if test="${requestScope.userIdError != null}"><p><c:out value="${requestScope.userIdError}" /></p></c:if>
		<c:if test="${requestScope.userPwError != null}"><p><c:out value="${requestScope.userPwError}" /></p></c:if>
		<c:if test="${requestScope.gradeError != null}"><p><c:out value="${requestScope.gradeError}" /></p></c:if>
		<table border="1">
			<tr>
				<td>ユーザID</td>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr>
				<td>ユーザパスワード</td>
				<td><input type="text" name="userPw" /></td>
			</tr>
			<tr>
				<td>学部</td>
				<td>
					<select name="deptName">
						<c:forEach var="dept" items="${requestScope.deptList}">
							<option value="${dept.deptName}"><c:out value="${dept.deptName}" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>学年</td>
				<td><input type="text" name="grade" /></td>
			</tr>
			<tr>
				<td>氏名</td>
				<td><input type="text" name="name" /></td>
			</tr>
		</table>
		<input type="submit" value="追加" />
	</form>
</body>
</html>