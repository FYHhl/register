<%--
  Created by IntelliJ IDEA.
  User: 13447
  Date: 2022/3/15
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        tr{
            padding: 15px 0;
        }
    </style>
</head>
<script>
    window.onload = function(){
        document.getElementById("img").onclick = function(){
            // 加一个time 防止缓存
            this.src="checkCodeServlet?time="+new Date().getTime();
        }
    }
</script>
<body>
<h2 style="text-align: center">请登录</h2>
<table border="1px solid" style="margin: auto;border-collapse: collapse">
    <form action="login" method="post">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="img" src="checkCodeServlet"></td>
        </tr>
        <tr>
            <td>验证码:</td>
            <td><input type="text" name="captcha" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交"/>
                <a href="register.jsp">暂无账号，去注册</a>
            </td>
        </tr>
    </form>
</table>
<div style="text-align: center"><%=request.getAttribute("c_error") == null ? "" : request.getAttribute("c_error")%></div>
<div style="text-align: center"><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error") %></div>
</body>
</html>