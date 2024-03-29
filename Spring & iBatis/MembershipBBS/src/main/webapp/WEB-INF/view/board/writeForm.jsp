<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>새 글 쓰기</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/style/main.css" />
</head>
<body>
	
	<form action="<%= request.getContextPath() %>/board/write"
		method="post">
		
		<input type="hidden" name="p_bno" value="${models.p_bno}" />
		
		<table>
			<tr>
				<td>제목</td>
				<td>
					<c:if test="${empty models.p_bno}">
						<input type="text" name="subject" value="" />
					</c:if>
					<c:if test="${not empty models.p_bno}">
						<input type="text" name="subject" value="Re: ${models.pBoard.subject}" />
					</c:if>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="6" cols="30"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글쓰기" />
					<input type="reset" value="초기화" />
					<input type="button" value="취소" onclick="history.back();" />
				</td>
			</tr>
		</table>
		
	</form>
	
</body>
</html>