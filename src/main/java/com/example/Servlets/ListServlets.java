package com.example.Servlets;

import com.example.Model.User;
import com.example.Util.DButil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/list",loadOnStartup = 1)
public class ListServlets extends HttpServlet {
    Connection connection=null;
    PreparedStatement preparedStatement=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            connection = DButil.getConnection();
            String sql="select * from sys_user";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userList=new ArrayList<>();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                User user=new User(id,userName,password);
                userList.add(user);
            }
            req.getServletContext().setAttribute("userList",userList);
            resp.sendRedirect(req.getContextPath()+"/list.jsp");
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