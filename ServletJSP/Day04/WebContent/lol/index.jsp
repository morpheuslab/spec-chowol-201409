<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String target = request.getParameter("target") + ".jsp";
%>
<!DOCTYPE html>
<html>
<head>
<title>모듈화</title>
</head>
<body>
	<table width="500" border="1" cellpadding="5" align="center">
		<tr>
			<td colspan="2">
				<jsp:include page="include/top.jsp" flush="false" />
			</td>
		</tr>
		<tr>
			<td valign="top">
				<jsp:include page="include/menu.jsp" flush="false" />
			</td>
			<td width="80%" valign="middle">
				<jsp:include page="<%= target %>" flush="false" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<jsp:include page="include/bottom.jsp" flush="false" />
			</td>
		</tr>
	</table>
</body>
</html>













































