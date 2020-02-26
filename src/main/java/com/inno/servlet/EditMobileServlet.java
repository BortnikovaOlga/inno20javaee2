package com.inno.servlet;

import com.inno.dao.MobileDao;
import com.inno.pojo.Mobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editmobile")
public class EditMobileServlet extends HttpServlet {
    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("PageTitle", "Edit Mobile");
        req.setAttribute("PageBody", "editmobile.jsp");
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        String mobileId = req.getParameter("id");
        if (mobileId == null) {
            throw new ServletException("Missing parameter id");
        }
        Mobile mobile = mobileDao.getMobileById(Integer.valueOf(mobileId));

        if (mobile == null) {
            resp.setStatus(404);
            req.getRequestDispatcher("/notfound.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("PageTitle", "Edit Mobile");
        req.setAttribute("PageBody", "edit.jsp");

        req.setAttribute("mobile", mobile);
        req.getRequestDispatcher("/layout.jsp")
                .forward(req, resp);
    }
}