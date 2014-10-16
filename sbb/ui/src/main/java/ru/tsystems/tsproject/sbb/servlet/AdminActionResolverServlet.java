package ru.tsystems.tsproject.sbb.servlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet redirects to appropriate administrator service page. That redirect depends on param adminAction,
 * which user could fill on administrator default page
 * @author  Nikita Efremov
 * @since   1.0
 */
public class AdminActionResolverServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AdminActionResolverServlet.class);

    /**
     * Method proceeds both GET and POST requests. It redirects to appropriate administrator service page
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chosenAction = request.getParameter("adminAction");
        log.log(Level.DEBUG, "chosenAction=" + chosenAction);

        if (chosenAction == null) {
            response.sendRedirect("/ui/administrator/administratorMain.jsp");
        } else if (chosenAction.equals("Add new station")) {
            response.sendRedirect("/ui/administrator/station/createNewStation.jsp");
        } else if (chosenAction.equals("Watch all stations")) {
            response.sendRedirect("/ui/administrator/station/ViewAllStations");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/pageIsNotReady.jsp");
            requestDispatcher.forward(request, response);
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
