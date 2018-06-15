<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>【リンクメニュー】</title>

		<script language="JavaScript"><!--

			function setActionName(actionname) {
				document.mainform.actionname.value=actionname;
			}

		// --></script>


</head>
<body>
		<p>
		【リンクメニュー画面】
		</p>

		<form name="mainform" method="post" action="ControlServlet">
			<input type="hidden" name="actionname" value="">

			<input type="submit" name="listButton" value="一　覧" onClick=setActionName("linkList")>
			<br><br>
			<input type="submit" name="entryButton" value="登　録" onClick=setActionName("linkEntry")>
		</form>

</body>
</html>