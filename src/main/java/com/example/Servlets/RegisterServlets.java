package com.example.Servlets;

import com.example.Util.DButil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(urlPatterns = "/register",loadOnStartup = 1)
public class RegisterServlets extends HttpServlet {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        try{
            connection = DButil.getConnection();
            String sql="insert into sys_user (user_name,password) values (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            int i = preparedStatement.executeUpdate();
            System.out.println("影响了"+i+"条");
            if (i!=0){
                System.out.println("注册成功！");
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }
        } catch (Exception e) {
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
    }
}
