package ru.tsystems.tsproject.sbb.servlet.common;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet redirects to appropriate common service page. That redirect depends on param commonAction,
 * which user could fill on common default page
 * @author  Nikita Efremov
 * @since   1.0
 */

public class CommonActionResolverServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CommonActionResolverServlet.class);

    /**
     * Method proceeds both GET and POST requests. It redirects to appropriate common service page
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
        String chosenAction = request.getParameter("commonAction");
        log.log(Level.DEBUG, "chosenAction=" + chosenAction);

        if (chosenAction == null) {
            response.sendRedirect("/ui/index.jsp");
        } else if (chosenAction.equals("Watch station timetable")) {
            response.sendRedirect("/ui/common/searchStation.jsp");
        } else if (chosenAction.equals("Search train by stations and date")) {
            response.sendRedirect("/ui/common/searchStationDateTrain.jsp");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pageIsNotReady.jsp");
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