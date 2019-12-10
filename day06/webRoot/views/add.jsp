<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>table</title>
    <style>
        div {
            margin-top: 8px;
        }
    </style>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/add" method="post">
    <h4>用户信息提交</h4>
    <div>
        <label for="name">姓名: </label><input type="text" id="name" name="name" required>
    </div>
    <div>
        <label for="age">年龄: </label><input type="text" id="age" name="age" required>
    </div>
    <div>
        <button type="reset">重置</button>
        <button type="submit">提交</button>
    </div>
</form>

<form action="${pageContext.servletContext.contextPath}/add?cmd=update" method="post">
	<input name="id"  value="${student.id}"  hidden/>
    <h4>用户信息更新页面</h4>
    <div>
        <label for="name">姓名: </label><input type="text" id="name" name="name" value="${student.name}" required>
    </div>
    <div>
        <label for="age">年龄: </label><input type="text" id="age" name="age" value="${student.age}" required>
    </div>
    <div>
        <button type="submit">更新信息</button>
    </div>
</form>

</body>
</html>