<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="context-style-type" content="text/css" />
<style type="text/css">
div{
	text-align: center;
}
th{
	background-color:#FF9900;
}
</style>
<title>大学の図書管理システム</title>
</head>
<body>

<div id="request">
<h1>リクエスト本の状況一覧</h1>
<hr />
<br />


<c:choose>
	<c:when test="${requestScope.requestList.size() >= 1}">
		<table border="1" align="center">
			<tr>
				<th>要望申請日</th>
				<th>氏名</th>
				<th>タイトル</th>
				<th>作者名</th>
				<th>出版社</th>
				<th></th>
			</tr>

			<c:forEach var="request" items="${requestScope.requestList}">
				<tr>
					<td><c:out value="${request.requestDate}" /></td>
					<td><c:out value="${changer.userIdToName(request.userId)}" /></td>
					<td><c:out value="${request.title}" /></td>
					<td><c:out value="${request.author}" /></td>
					<td><c:out value="${request.publisher}" /></td>
					<td>
						<form action="requestList" method="post">
							<input type="hidden" name="targetId" value="${request.requestId}" />
							<input type="submit" name="btn" value="承認" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>

	<c:otherwise>
		<p>該当データがありません</p>
	</c:otherwise>
</c:choose>
<br />
<br />
	<a href="requestList">戻る</a>
<br />
<br />
	<a href="admin">メニュー画面へ戻る</a>
</div>
</body>
</html>