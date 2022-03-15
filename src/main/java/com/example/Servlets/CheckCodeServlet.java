package com.example.Servlets;

import com.example.Model.Captcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("image/jpg");
        int width = 100;
        int height = 50;
        Captcha c = Captcha.getIntance();
        // 只设置宽高
        c.set(width, height);
        // 也可以指定宽高和生成个数以及生成文本
        // c.set(width, height, 8, "asncjkhashd213456");
        // 获取验证码
        String checkCode_session = c.generateCheckcode();
        // 将验证码存入session
        request.getSession().setAttribute("checkCode_session",
                checkCode_session);
        // 获取输出流
        OutputStream os = response.getOutputStream();
        // 把图片输出到页面
        ImageIO.write(c.generateCheckImg(checkCode_session), "jpg", os);
    }
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
