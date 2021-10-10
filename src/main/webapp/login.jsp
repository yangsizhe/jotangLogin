<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <h1>登录</h1>
    <form action="${pageContext.request.contextPath}/dologin" method="post">
        账&nbsp&nbsp号：<input type="text" name="id"/><br/>
        密&nbsp&nbsp码：<input type="password" name="password"/><br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>

