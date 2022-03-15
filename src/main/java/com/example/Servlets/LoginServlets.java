package com.example.Servlets;

import com.example.Util.DButil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/login",loadOnStartup = 1)
public class LoginServlets extends HttpServlet {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    Boolean isLogin=false;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String captcha = req.getParameter("captcha");
        HttpSession session = req.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        // 删除session中存储的验证码
        session.removeAttribute("checkCode_session");
        // 3.先判断验证码是否正确
        if (checkCode_session != null && checkCode_session.equalsIgnoreCase(captcha)) {
            // 忽略大小写比较
            // 验证码正确
            // 判断用户名和密码是否一致
            try {
                connection = DButil.getConnection();
                String sql="select * from sys_user where user_name=? and password=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,userName);
                preparedStatement.setString(2,password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    System.out.println("登陆成功");
                    isLogin=true;
                    session.setAttribute("user", userName);
                    // 重定向到success.jsp
                    resp.sendRedirect(req.getContextPath() + "/list");
                }else{
                    System.out.println("登陆不成功,用户名或密码错误！");
                    req.getServletContext().setAttribute("login_error", "用户名或密码错误");
                    resp.sendRedirect(req.getContextPath()+"/login.jsp");
                }
                session.setAttribute("isLogin",isLogin);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (connection!=null){
                        connection.close();
                    }if (preparedStatement!=null){
                        preparedStatement.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else {
            // 验证码不一致
            // 存储提示信息到request
            req.setAttribute("c_error", "验证码错误");
            // 转发到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }
}
