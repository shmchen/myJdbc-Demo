<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>学生信息列表页面</title>
    <style>
        tr {
            text-align: center;
        }
    </style>
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/views/edit.jsp">添加</a>
<table border="1" cellpadding="0" cellspacing="0" width="65%">
    <tr style="background-color: orange;">
        <td>学号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="student">
    <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.age}</td>
        <td><a href="${pageContext.servletContext.contextPath}/student?cmd=delete&id=${student.id}">删除</a> | <a href="${pageContext.servletContext.contextPath}/student?cmd=edit&id=${student.id}">编辑</a></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>