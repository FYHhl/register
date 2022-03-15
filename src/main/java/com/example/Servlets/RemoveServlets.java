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

@WebServlet(urlPatterns = "/remove",loadOnStartup = 1)
public class RemoveServlets extends HttpServlet {
    PreparedStatement preparedStatement=null;
    Connection connection=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            connection = DButil.getConnection();
            String sql="delete from sys_user where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            int i = preparedStatement.executeUpdate();
            System.out.println("影响了"+i+"条");
            if(i!=0){
                System.out.println("删除成功");
                resp.sendRedirect(req.getContextPath()+"/list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭结果集
            try {
                //第6步：释放资源
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}