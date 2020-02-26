package com.inno.servlet;

import com.inno.dao.MobileDao;
import com.inno.pojo.Mobile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editmobile2")
public class EditMobileServlet2 extends HttpServlet {

    private MobileDao mobileDao;

    @Override
    public void init() throws ServletException {
        mobileDao = (MobileDao) getServletContext().getAttribute("dao");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String id=req.getParameter("id");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String manufacturer = req.getParameter("manufacturer");
        Mobile mobile = new Mobile(Integer.valueOf(id), model, Integer.valueOf(price), manufacturer);
        mobileDao.updateMobileById(mobile);
        resp.sendRedirect(req.getContextPath() + "/allmobiles");
    }
}
