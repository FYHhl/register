<%--
  Created by IntelliJ IDEA.
  User: 13447
  Date: 2022/3/15
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<html>
<head>
    <title>用户展示</title>
</head>
<body>
<table>
    <tr>
        <td>id</td>
        <td>用户名</td>
        <td>密码</td>
    </tr>
    <c:forEach var="item" items="${applicationScope.userList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.userName}</td>
            <td>${item.password}</td>
            <td><a href="remove?id=${item.id}">删除</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="register.jsp">注册</a></td>
    </tr>
</table>
</body>
</html>

