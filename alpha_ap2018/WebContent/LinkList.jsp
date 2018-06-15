<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="exercise06.link.bean.LinkBean"%>
<%@ page import="exercise06.link.bean.LinkCategoryBean"%>

<%
	ArrayList<LinkBean> linkList = (ArrayList<LinkBean>)request.getAttribute("linkList");
	ArrayList<LinkCategoryBean> categoryList = (ArrayList<LinkCategoryBean>)request.getAttribute("categoryList");
	String searchCtgId = (String)request.getAttribute("searchCtgId");
 %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【リンク一覧】</title>

		<script language="JavaScript"><!--

			function setActionName(actionname) {
				document.mainform.actionname.value=actionname;
			}

			function setLinkId(linkId) {
				document.mainform.linkId.value=linkId;
			}


		// --></script>


</head>
<body>
		<p>
		【リンク一覧画面】
		</p>

		<form name="mainform" method="post" action="ControlServlet">

			<table border=1>
				<tr>
					<td>リンク種別</td>
					<td>
						<select name="searchCtgId">
							<option value="">すべて</option>
						<%
							Iterator categoryIte = categoryList.iterator();
							while (categoryIte.hasNext()) {
								LinkCategoryBean categoryBean = (LinkCategoryBean)categoryIte.next();
								String selected = "";
								if (categoryBean.getCategoryId().equals(searchCtgId)) {
									selected = "selected";
								}
						%>
							<option value="<%= categoryBean.getCategoryId() %>" <%= selected %>><%= categoryBean.getCategoryName() %></option>
						<%
							}
						%>
						</select>
					</td>
					<td>
						<input type="submit" name="listButton" value="絞　込" onClick=setActionName("linkList")>
					</td>
				</tr>
			</table>

			<br>

			<table border=1>
				<tr>
					<th>リンクID</th>
					<th>リンク名</th>
					<th>URL</th>
					<th>変更</th>
					<th>削除</th>
				</tr>
<%
		if (linkList != null) {
			Iterator it = linkList.iterator();
			while (it.hasNext()) {
				LinkBean linkBean = (LinkBean)it.next();
 %>
				<tr>
					<td>
						<%= linkBean.getLinkId() %>
					</td>
					<td>
						<%= linkBean.getLinkName() %>
					</td>
					<td>
						<%= linkBean.getUrl() %>
					</td>
					<td>
						<input type="submit" name="editButton" value="変更"
							onClick="setLinkId('<%= linkBean.getLinkId() %>');setActionName('linkEdit');">
					</td>
					<td>
						<input type="submit" name="deleteButton" value="削除"
							onClick="setLinkId('<%= linkBean.getLinkId() %>');setActionName('linkDelete');">
					</td>
				</tr>

<%
			}
		}
 %>
			</table>

			<input type="hidden" name="actionname" value="" />
			<input type="hidden" name="linkId" value="" />

			<p>
				<input type="submit" name="buckButton" value="戻　る" onClick=setActionName("linkMenu")>
			</p>
		</form>

</body>
</html>