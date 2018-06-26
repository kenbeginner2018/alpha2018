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
  width: 145px;
  line-height: 37px;
  color: #2bb6c1;
  border:solid 1px #2bb6c1;
  border-radius: 20px;
  -webkit-transition: 0.3s;
  -moz-transition: 0.3s;
  -o-transition: 0.3s;
  -ms-transition: 0.3s;
  transition: 0.3s;
  margin-left: 0;
  margin-right: 0;
}
.link_button:hover{
  background: #2bb6c1;
  color: #fff;
}
.linkmenu_button {
  display: block;
  text-decoration: none;
  height:35px;
  width: 115px;
  line-height: 37px;
  line-width: ;
  color: #2bb6c1;
  border:solid 1px #2bb6c1;
  border-radius: 20px;
  -webkit-transition: 0.3s;
  -moz-transition: 0.3s;
  -o-transition: 0.3s;
  -ms-transition: 0.3s;
  transition: 0.3s;
  margin-left: 0;
  margin-right: 0;
}
.linkmenu_button:hover{
  background: #2bb6c1;
  color: #fff;
}
a:visited {
  color:#2c4641;
}

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
<title>返却画面</title>
</head>
<body>
	<h1>返却・検索</h1>

	<hr size="2" align="center" />
		<form action="ReturnServlet" method="get">
			ユーザーID　<input type="text" name="userId" value=""  />
			<br></br>
			識別ラベル　<input type="text" name="labelId" value="" />
			<br></br>
			<input type="submit" value="検索" id="text_button"/>
		</form>

	<h1>貸出一覧</h1>

	<hr size="2" align="center" />
		<c:if test="${not empty requestScope.message}">	<%--messageが空でなけば表示 --%>
			<c:out value="${message}" />
			<p><a class="linkmenu_button" href="ReturnServlet">貸出一覧へ戻る</a></p>
		</c:if>
		<p><a class="link_button" href="Admin.jsp">メニュー画面へ戻る</a></p>

	<c:if test="${requestScope.RentalList != null}"><%--返却フラグの判定 --%>
		<table border="1" id = "table">
			<tr>
				<th>識別ラベル</th>
				<th>タイトル名</th>
				<th>ユーザーID</th>
				<th>氏名</th>
				<th>返却予定日</th>
				<th></th>
			</tr>
			<c:forEach var="rtn" items="${requestScope.RentalList}">
				<c:if test="${rtn.returnFlag == false}">
					<tr>
						<td><c:out value="${rtn.label}" /> </td>
						<td><c:out value="${Changer.labelToTitle(rtn.label)}" /> </td>
						<td><c:out value="${rtn.userId}" /> </td>
						<td><c:out value="${Changer.userIdToName(rtn.userId)}" /> </td>
						<c:choose>
							<c:when test="${rtn.extendFlag == true}">		<%--延長フラグの判定--%>
								<td><c:out value="${Changer.rentalDateToExtendDate(rtn.rentalDate)}" /> </td>
							</c:when>
							<c:otherwise>
								<td><c:out value="${rtn.rentalDate}" /> </td>
							</c:otherwise>
						</c:choose>
						<td>
							<form action="ReturnServlet" method="post">
								<input type="submit" name="btn" value="返却" />
								<input type="hidden" name="userId" value="${rtn.userId}" />
								<input type="hidden" name="rentalId" value="${rtn.rentalId}" />
								<input type="hidden" name="labelId" value="${rtn.label}" />
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
	<p><c:out value="${errorMessage}" /></p>
</body>
</html>