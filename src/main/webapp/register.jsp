<%--
  Created by IntelliJ IDEA.
  User: 13447
  Date: 2022/3/15
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<table>
    <form action="register" method="post">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="注册"></td>
        </tr>
        <tr>
            <td><a href="login.jsp">已有账号，去登录</a></td>
        </tr>
    </form>
</table>
</body>
</html>
