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
.link_button {
  display: block;
  text-decoration: none;
  height:35px;
  width: 120px;
  line-height: 37px;
  color: #2bb6c1;
  border:solid 1px #2bb6c1;
  border-radius: 20px;
  -webkit-transition: 0.3s;
  -moz-transition: 0.3s;
  -o-transition: 0.3s;
  -ms-transition: 0.3s;
  transition: 0.3s;
  margin-left: auto;
  margin-right: auto;
}
.link_button:hover{
  background: #2bb6c1;
  color: #fff;
}
.linkmenu_button {
  display: block;
  text-decoration: none;
  height:35px;
  width: 150px;
  line-height: 37px;
  color: #2bb6c1;
  border:solid 1px #2bb6c1;
  border-radius: 20px;
  -webkit-transition: 0.3s;
  -moz-transition: 0.3s;
  -o-transition: 0.3s;
  -ms-transition: 0.3s;
  transition: 0.3s;
  margin-left: auto;
  margin-right: auto;
}
.linkmenu_button:hover{
  background: #2bb6c1;
  color: #fff;
}
a:visited {
  color:#2c4641;
}
#table th,#table td {
  padding: 15px;
}
#table tr {
  background: #F8F8F8;
}
#table tr:first-child {
  background: #8DC4AA;
}

#table th {;
  color: #2c4641;
  border-bottom: solid 1px #759786;
}
#table td {
  color: #2c4641;
  border-bottom: solid 1px #A7D2BE;
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
		<table id="table" border="1" align="center">
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
		<a class="link_button" href="requestList">戻る</a>
	</c:otherwise>
</c:choose>
<br />
<br />
	<a class="linkmenu_button" href="admin">メニュー画面へ戻る</a>
</div>
</body>
</html>