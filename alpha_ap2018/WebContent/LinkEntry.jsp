<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="exercise06.link.bean.LinkCategoryBean" %>

<%
	ArrayList<LinkCategoryBean> categoryList = (ArrayList<LinkCategoryBean>)request.getAttribute("categoryList");
 %>

 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【リンク登録】</title>

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

			// 桁数字入力チェック
			function checkNumber(obj, figure, msg) {
				if(!obj.value.match("¥¥d{" + figure + "}")){
					window.alert(msg + 'は' + figure + '桁の数字で入力してください');
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

				if(!checkEssential(document.mainform.linkId, "リンクID")){
					// リンクIDの未入力チェック
					return false;
				}else if(!checkNumber(document.mainform.linkId, "5", "リンクID")){
					// リンクIDの数値チェック
					return false;
				}else if(!checkEssential(document.mainform.linkName, "リンク名")){
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
		【リンク登録画面】
		</p>

		<form name="mainform" method="post" action="ControlServlet">
			<input type="hidden" name="actionname" value="" />

			<table border=1>
				<tr>
					<td align="center">リンクID</td>
					<td><input type="text" name="linkId" size="6" maxlength="5" /></td>
				</tr>
				<tr>
					<td align="center">リンク種別</td>
					<td>
						<select name="categoryId">
						<%
							Iterator categoryIte = categoryList.iterator();
							while (categoryIte.hasNext()) {
								LinkCategoryBean categoryBean = (LinkCategoryBean)categoryIte.next();
						%>
							<option value="<%= categoryBean.getCategoryId() %>"><%= categoryBean.getCategoryName() %></option>
						<%
							}
						%>

					</td>
				</tr>
				<tr>
					<td align="center">リンク名</td>
					<td><input type="text" name="linkName" size="50" maxlength="40" /></td>
				</tr>
				<tr>
					<td align="center">URL</td>
					<td><input type="text" name="url" size="50" maxlength="200" /></td>
				</tr>
			</table>

			<p>
			<input type="submit" name="entryButton" value="登　録"  onClick=setActionName("linkEntryResult");return(errorCheck()) />
			<input type="submit" name="buckButton" value="戻　る" onClick=setActionName("linkMenu") />
			</p>
		</form>

</body>
</html>