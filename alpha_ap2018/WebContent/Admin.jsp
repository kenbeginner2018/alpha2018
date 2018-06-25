<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>メニュー画面</title>
<style>button.button4 {
    font-size: 1.4em;
	 padding: 10px 30px;
    font-weight: bold;

    padding: 10px 30px;

    color: #FFFFFF;
    border-style: none;
button.button4:hover {
    background-color: #24d;
    color: #fff;
}</style>
</head>
<body>
<div style="margin:auto;text-align:center;aligh:center;">

	<h1>メニュー</h1>
    <div style="">


    	<table border="3px" style="margin:5%;" align="center">
    		<tr>
    			<td><form action="userlist" method="get">
			<input type="submit" value="ユーザー一覧"name="userList" style="width:120px;height:40px;"class="button4"/>
		</form></td>
    			<td><form action="BookSearch" method="get">
			<input type="submit" value="蔵書管理"name="BookSearch" style="width:120px;height:40px;" class="button4"/>
		</form>
    			</td>
    			<td><form action="RentalServlet" method="get">
			<input type="submit" value="貸出"name="ReturnServlet" style="width:120px;height:40px;" class="button4"/>
		</form></td>
    		</tr>
    		<tr>
    			<td><form action="reserve" method="get">
			<input type="submit" value="予約状況一覧"name="Reserve"  style="width:120px;height:40px;"class="button4"/>
		</form>
    			</td>
    			<td><form action="requestList" method="get">
			<input type="submit" value="リクエスト一覧"name="RequestListServlet" style="width:120px;height:40px;"class="button4">
			</input>
		</form></td>
    			<td><form action="ReturnServlet" method="get">
			<input type="submit" value="返却"name="ReturnServlet" style="width:120px;height:40px;"class="button4"/>
		</form></td>
    		</tr>
    	</table>

<div style="border:1px;">
<form action="admin" method="post">
		<input type="submit" name="btn" value="ログアウト" style="width:120px;height:40px;"class="button4"/>
	</form>
</div>
</div>
</div>
</body>
</html>