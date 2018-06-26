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
	<a href="userlist">ユーザー一覧へ戻る</a>
	<h1>ユーザ詳細画面</h1>
	<hr />
	<!-- エラーメッセージを出力する -->
	<c:if test="${requestScope.notNullError != null}"><p><c:out value="${requestScope.notNullError}" /></p></c:if>
	<c:if test="${requestScope.gradeError != null}"><p><c:out value="${requestScope.gradeError}" /></p></c:if>
	<form action="userdata" method="post">
		<p>
			ユーザID <c:out value="${requestScope.targetUser.userId}" />
			<br />
			学部
			<select name="deptName">
				<c:forEach var="dept" items="${requestScope.deptList}">
					<c:choose>
						<c:when test="${dept.deptId == targetUser.deptId}">
							<option value="${dept.deptName}" selected ><c:out value="${dept.deptName}" /></option>
						</c:when>
						<c:otherwise>
							<option value="${dept.deptName}"><c:out value="${dept.deptName}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			　学年 <input type="text" name="grade" value="${requestScope.targetUser.grade}" />
			　氏名 <input type="text" name="name" value="${requestScope.targetUser.name}" />
		</p>
		<input type="hidden" name="targetId" value="${requestScope.targetUser.userId}" />
		<table>
			<tr>
				<td width="100px"><input type="submit" name="btn" value="更新" /></td>
				<td width="100px"><input type="submit" name="btn" value="RESET" /></td>
				<td width="100px"><input type="submit" name="btn" value="削除" /></td>
			</tr>
		</table>
	</form>
	<hr />
	<h2>借りている本の一覧</h2>
	<c:choose>
		<c:when test="${requestScope.bookList.size() >= 1}">
			<table border="1">
				<tr>
					<th>タイトル</th>
					<th>ラベル</th>
				</tr>
				<c:forEach var="book" items="${requestScope.bookList}">
					<tr>
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.label}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<p>該当データ無し</p>
		</c:otherwise>
	</c:choose>
</body>
</html>