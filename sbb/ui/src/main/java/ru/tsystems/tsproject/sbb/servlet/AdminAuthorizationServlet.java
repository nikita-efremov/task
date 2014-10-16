package ru.tsystems.tsproject.sbb.servlet;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.bean.AdminLoginBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class AdminAuthorizationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminAuthorizationServlet.class);

    /**
     * Method proceeds both GET and POST requests. It analyze login and password from login page, which was inputted by user.
     * If credentials is valid, method adds "user" attribute to HttpSession object
     * @param request   an {@link javax.servlet.http.HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link javax.servlet.http.HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception java.io.IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception javax.servlet.ServletException  if the request for the GET
     *                                  could not be handled
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("loginAction");
        if (action == null) {
           response.sendRedirect("/ui/adminAuthorization.jsp");
        } else {
            AdminLoginBean adminLoginBean = new AdminLoginBean();
            adminLoginBean.setLogin(request.getParameter("login"));
            adminLoginBean.setPassword(request.getParameter("password"));
            adminLoginBean.validate();
            if (adminLoginBean.isValidationFailed()) {
                request.setAttribute("loginResult", adminLoginBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminAuthorization.jsp");
                requestDispatcher.forward(request, response);
            } else {
                String loginExpected = getServletConfig().getInitParameter("login");
                String passExpected = getServletConfig().getInitParameter("password");
                if ((loginExpected.equals(adminLoginBean.getLogin()))
                        && (passExpected.equals(adminLoginBean.getPassword()))) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("user", "admin");
                    httpSession.setMaxInactiveInterval(30*60);
                    response.sendRedirect("/ui/administrator/administratorMain.jsp");
                } else {
                    adminLoginBean.setValidationMessage("Invalid credentials");
                    request.setAttribute("loginResult", adminLoginBean);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/adminAuthorization.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        }
    }

    /**
     * Method proceeds GET request. It delegates processing to method processRequest(..)
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception ServletException  if the request for the GET
     *                                  could not be handled
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Method proceeds POST request. It delegates processing to method processRequest(..)
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @exception IOException   if an input or output error is
     *                              detected when the servlet handles
     *                              the GET request
     *
     * @exception ServletException  if the request for the GET
     *                                  could not be handled
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
