package com.inno.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AppFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(AppFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        String loginURI = httpRequest.getContextPath() + "/loginservlet";

        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // the user is already logged in and he's trying to login again
            // then forward to the homepage
            httpRequest.getRequestDispatcher("/mobiles/").forward(servletRequest, servletResponse);
            //filterChain.doFilter(servletRequest, servletResponse);

        } else if (isLoggedIn || isLoginRequest) {
            // for other requested pages that do not require authentication
            // or the user is already logged in, continue to the destination

            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            // the user is not logged in, and the requested page requires
            // authentication, then forward to the login page
            httpRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("destroy Filter");
    }
}
