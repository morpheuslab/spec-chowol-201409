<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글읽기</title>
</head>
<body>

<table>
	<tr>
		<td>글번호</td>
		<td>${board.bno}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${board.subject}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${board.content}</td>
	</tr>
	<tr>
		<td>글쓴이</td>
		<td>${board.writer}</td>
	</tr>
	<tr>
		<td>글쓴날짜</td>
		<td>${board.writedate}</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${board.hitcount}</td>
	</tr>
</table>

</body>
</html>