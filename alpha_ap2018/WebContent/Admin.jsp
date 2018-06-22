<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニュー画面</title>
</head>
<body>
<div style="width:125px;margin:auto;">

	<h1>メニュー</h1>
    <div>
    		<p>
        <form action="userlist" method="get">
			<input type="submit" value="ユーザー一覧"name="userList" style="position: absolute; left: 20%; top: 30%;padding: 20px 40px"/>
		</form>

		<form action="BookSearch" method="get">
			<input type="submit" value="蔵書管理"name="BookSearch"style="position:absolute; left: 45%; top: 30%;padding: 20px 40px" />

		</form>

		<form action="RentalServlet" method="get">
			<input type="submit" value="貸出"name="ReturnServlet"style="position:absolute; left:70%; top: 30%;padding: 20px 50px"/>
		</form>
			</p>

			<p>
		<form action="Reserve" method="get">
			<input type="submit" value="予約状況一覧"name="Reserve"style="position:absolute; left: 20%; top: 50%;padding: 20px 35px">
			</input>
		</form>

		<form action="RequestListServlet" method="get">
			<input type="submit" value="リクエスト一覧"name="RequestListServlet"style="position:absolute; left: 45%; top: 50%;padding: 20px 30px">
			</input>
		</form>

		<form action="ReturnServlet" method="get">
			<input type="submit" value="返却"name="ReturnServlet"style="position:absolute; left:70%; top: 50%;padding: 20px 50px"/>
		</form>

			</p>

 </div>
    <p><c:out value="${userId}" /></p>
</div>
</body>
</html>