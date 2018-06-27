<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>ユーザー一覧</title>
</head>
<body>
	<a href="admin">メニュー画面へ戻る</a>
	<h1>ID検索</h1>
	<form action="userlist" method="post">
		<input type="text" name="searchId" />
		<input type="submit" name="btn" value="検索" />
		<input type="submit" name="btn" value="ユーザー追加" />
	</form>
	<hr />
	<h2>ユーザー一覧</h2>
	<c:choose>
		<c:when test="${requestScope.userList != null}">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>氏名</th>
					<th>学部</th>
					<th>学年</th>
					<th></th>
				</tr>
				<c:forEach var="user" items="${requestScope.userList}">
					<tr>
						<td><c:out value="${user.userId}" /></td>
						<td><c:out value="${user.name}" /></td>
						<td>
							<c:forEach var="dept" items="${requestScope.deptList}">
								<c:if test="${user.deptId == dept.deptId}">
									<c:out value="${dept.deptName}" />
								</c:if>
							</c:forEach>
						</td>
						<td align="center" ><c:out value="${user.grade}" /></td>
						<td>
							<form action="userdata" method="get">
								<input type="submit" name="btn" value="詳細" />
								<input type="hidden" name="targetId" value="${user.userId}" />
							</form>
						</td>
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