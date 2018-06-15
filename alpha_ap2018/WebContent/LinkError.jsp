<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String errorMsg = (String)request.getAttribute("errorMsg");
%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【エラー】エラー</title>
</head>
<body>
		<p>
		【エラー画面】
		</p>
		<div>
			<font color="red"><%= errorMsg %></font>
		<div align="center">

</body>
</html>