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
input#submit_button {
	display: inline-block;
    padding: 0.5em 1em;
    text-decoration: none;
    border-radius: 4px;
    color: #ffffff;
    background-image: -webkit-linear-gradient(#6795fd 0%, #67ceff 100%);
    background-image: linear-gradient(#6795fd 0%, #67ceff 100%);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.29);
    border-bottom: solid 3px #5e7fca;
}
input#text_button {
	webkit-box-shadow: 0px 1px rgba(255, 255, 255, 0.5);
	moz-box-shadow: 0px 1px rgba(255, 255, 255, 0.5);
	box-shadow: 0px 1px rgba(255, 255, 255, 0.5);
	webkit-border-radius: 3px;
	moz-border-radius: 3px;
	border-radius: 3px;
}
input:focus {
  border:solid 1px #EEA34A;
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

<div id="reservediv">
<h1>予約状況検索</h1>
	<form action="reserve" method="post">
		<table align="center">
		<tr>
			<td>本のタイトル</td>
			<td><input id="text_button" type="text" name="title" value="" size="40" /></td>
			<td><input id="submit_button" type="submit" name="btn" value="検索" /></td>
		</tr>
		</table>
	</form>
<br />
<hr />
<br />
<h2>予約状況一覧</h2>
<c:choose>
	<c:when test="${requestScope.reserveList.size() >= 1}">
		<table id="table" border="1" align="center">
			<tr>
				<th>予約日</th>
				<th>本のタイトル</th>
				<th>氏名</th>
				<th></th>
			</tr>
			<c:forEach var="reserve" items="${requestScope.reserveList}">
				<tr>
					<td><c:out value="${reserve.reserveDate}" /></td>
					<td><c:out value="${changer.labelToTitle(reserve.label)}" /></td>
					<td><c:out value="${changer.userIdToName(reserve.userId)}" /></td>
					<td>
						<form action="reserve" method="post">
							<input type="hidden" name="targetId" value="${reserve.reserveId}" />
							<input type="submit" name="btn" value="削除" />
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
	<a class="link_button" href="reserve">戻る</a>
<br />
	<a class="linkmenu_button" href="admin">　メニュー画面へ戻る</a>
</div>
</body>
</html>