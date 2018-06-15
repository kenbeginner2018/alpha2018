<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="exercise06.link.bean.LinkBean" %>

<%
	LinkBean linkBean = (LinkBean)request.getAttribute("linkBean");
	String action = (String)request.getAttribute("action");
 %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【リンク<%= action %>結果】</title>

		<script language="JavaScript"><!--

			function setActionName(actionname) {
				document.mainform.actionname.value=actionname;
			}

		// --></script>


</head>
<body>
		<p>
		【リンク<%= action %>結果画面】
		</p>

		<form name="mainform" method="post" action="ControlServlet">
			<input type="hidden" name="actionname" value="">

			<table border=1>
				<tr>
					<td>リンクID</td>
					<td><%= linkBean.getLinkId() %></td>
				</tr>
				<tr>
					<td>リンク種別</td>
					<td><%= linkBean.getCategoryName() %></td>
				</tr>
				<tr>
					<td>リンク名</td>
					<td><%= linkBean.getLinkName() %></td>
				</tr>
				<tr>
					<td>URL</td>
					<td><%= linkBean.getUrl() %></td>
				</tr>
			</table>

			<p>
			<input type="submit" name="buckButton" value="戻　る" onClick=setActionName("linkMenu")>
			</p>
		</form>

</body>
</html>