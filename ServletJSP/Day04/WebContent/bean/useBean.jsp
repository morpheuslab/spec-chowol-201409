<%@page import="java.io.ObjectOutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="user" type="bean.User" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java Bean 사용 (데이터 꺼내기)</title>
</head>
<body>

사용자정보<br/>
아이디: <jsp:getProperty name="user" property="id" /><%--= user.getId() --%><br/>
이름: <%= user.getName() %><br/>
비밀번호: <%= user.getPw() %><br/>
이메일: <%= user.getEmail() %><br/>
등록일: <%= user.getRegDate() %><br/>

<%
// 객체 저장
String savePath = "C:/applications/bean/";
File saveDir = new File(savePath);
// saveDir이 존재하지 않는다면
if (!saveDir.exists()) {
	saveDir.mkdirs();
}
// 객체 저장 파일 이름
String fileName = user.getId() + ".bean";
File beanFile = new File(saveDir, fileName);	// new File(savePath + fileName);
FileOutputStream fout = new FileOutputStream(beanFile);
ObjectOutputStream oout = new ObjectOutputStream(fout);
oout.writeObject(user);
oout.close();
%>

</body>
</html>

























