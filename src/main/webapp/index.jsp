<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath }/mailTest/sendEmail/0">发送邮件,不使用模板</a>
        <br>
        <a href="${pageContext.request.contextPath }/mailTest/sendEmail/1">发送邮件，使用模板</a>
    </body>
</html>