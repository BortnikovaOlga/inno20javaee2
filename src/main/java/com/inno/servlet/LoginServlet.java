package com.inno.servlet;

import com.inno.dao.UserDao;
import com.inno.pojo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = (UserDao) getServletContext().getAttribute("udao");
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        System.out.println(login + password);
        User user = userDao.getUser(login, password);
        System.out.println("----" + user);
        String destPage = "/login.jsp";

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            destPage = "/index.jsp";
        }
        //HttpSession session = request.getSession();
        //session.setAttribute("user", new User("name", "login", "password", "", ""));
        request.getRequestDispatcher(destPage).forward(request, response);
    }
}
