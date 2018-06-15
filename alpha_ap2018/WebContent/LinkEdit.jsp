<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="exercise06.link.bean.LinkBean"%>
<%@ page import="exercise06.link.bean.LinkCategoryBean"%>

<%
	ArrayList<LinkCategoryBean> categoryList = (ArrayList<LinkCategoryBean>)request.getAttribute("categoryList");
	LinkBean linkBean = (LinkBean)request.getAttribute("linkBean");
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【リンク変更】</title>

		<script language="JavaScript"><!--

			function setActionName(actionname) {
				document.mainform.actionname.value=actionname;
			}

			// 必須入力チェック
			function checkEssential(obj, msg) {
				if (obj.value=='') {
					window.alert(msg + 'を入力してください');
					return false;
				}
				return true;
			}

			// URL形式チェック
			function checkUrl(obj, msg) {
				if(!obj.value.match("^http:¥/¥/")){
					window.alert(msg + 'を正しく入力してください');
					return false;
				}
				return true;
			}

			// 入力チェック
			function errorCheck() {

				if(!checkEssential(document.mainform.linkName, "リンク名")){
					// リンク名の未入力チェック
					return false;
				}else if(!checkEssential(document.mainform.url, "URL")){
					// URLの未入力チェック
					return false;
				}else if(!checkUrl(document.mainform.url, "URL")){
					// URLの形式チェック
					return false;
				}

				return true;
			}

		// --></script>

</head>
<body>
		<p>
		【リンク変更画面】
		</p>

		<form name="mainform" method="post" action="ControlServlet">
			<input type="hidden" name="actionname" value="">
			<input type="hidden" name="linkId" value="<%= linkBean.getLinkId() %>">

			<table border=1>
				<tr>
					<td align="center">リンクID</td>
					<td><%= linkBean.getLinkId() %></td>
				</tr>
				<tr>
					<td align="center">リンク種別</td>
					<td>
						<select name="categoryId">
						<%
							Iterator categoryIte = categoryList.iterator();
							while (categoryIte.hasNext()) {
								LinkCategoryBean categoryBean = (LinkCategoryBean)categoryIte.next();
								String selected = "";
								if (categoryBean.getCategoryId().equals(linkBean.getCategoryId())) {
									selected = "selected";
								}
						%>
							<option value="<%= categoryBean.getCategoryId() %>" <%= selected %>><%= categoryBean.getCategoryName() %></option>
						<%
							}
						%>

					</td>
				</tr>
				<tr>
					<td align="center">リンク名</td>
					<td><input type="text" name="linkName" size="50" maxlength="40" value="<%= linkBean.getLinkName() %>"></td>
				</tr>
				<tr>
					<td align="center">URL</td>
					<td><input type="text" name="url" size="50" maxlength="200" value="<%= linkBean.getUrl() %>"></td>
				</tr>
			</table>

			<p>
			<input type="submit" name="editButton" value="変　更" onClick=setActionName("linkEditResult");return(errorCheck())>
			<input type="submit" name="buckButton" value="戻　る" onClick=setActionName("linkList")>
			</p>
		</form>

</body>
</html>