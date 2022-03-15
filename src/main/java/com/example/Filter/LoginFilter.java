package com.example.Filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class LoginFilter extends HttpFilter {
    public static final List<String> filterPath= Arrays.asList("/register.jsp","/login.jsp","/register","/login","/checkCodeServlet");
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=utf-8");
        String servletPath = req.getServletPath();
        if(filterPath.contains(servletPath)){
            System.out.println("拦截器捕获");
            chain.doFilter(req,res);
            return;
        }
        HttpSession session= req.getSession();
        Boolean isLogin=(Boolean)session.getAttribute("isLogin");
        System.out.println(isLogin);
        if(isLogin==null||!isLogin){
            res.sendRedirect(req.getContextPath()+"/login.jsp");
        }
        chain.doFilter(req,res);
    }
}
