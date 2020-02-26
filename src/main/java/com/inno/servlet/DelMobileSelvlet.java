package com.inno.servlet;

import com.inno.dao.MobileDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delmobile")
public class DelMobileSelvlet extends HttpServlet {
    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Delete Mobiles");
        req.setAttribute("PageBody", "delmobile.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");

        mobileDao.deleteMobileById(Integer.valueOf(id));

        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }

}
