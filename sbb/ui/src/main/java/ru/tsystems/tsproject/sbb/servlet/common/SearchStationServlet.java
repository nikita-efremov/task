package ru.tsystems.tsproject.sbb.servlet.common;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.ApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.StationModel;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Servlet launches searching new station, than it analyzes result and send to view
 * @author  Nikita Efremov
 * @since   1.0
 */

public class SearchStationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(SearchStationServlet.class);
    private StationModel stationModel;

    /**
     * Initialize servlet`s attribute - stationModel
     */
    public void init() {
        stationModel = ApplicationContext.getStationModel();
    }

    /**
     * Method proceeds both GET and POST requests. It launches station searching, analyses result of searching, send result to view
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
        StationBean stationBean = new StationBean();
        stationBean.setName(request.getParameter("Station_name"));
        log.info("Servlet got bean: " + stationBean);
        String action = request.getParameter("stationSearchAction");
        if (action == null) {
            response.sendRedirect("/ui/common/searchStation.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/index.jsp");
        } else if (action.equals("watch timetable")) {
            stationBean = stationModel.findStation(stationBean);
            request.setAttribute("stationBean", stationBean);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/stationTimetable.jsp");
            requestDispatcher.forward(request, response);
        } else {
            ValidationBean validationBean = Validator.validate(stationBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("searchResult", stationBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/searchStation.jsp");
                requestDispatcher.forward(request, response);
            } else {
                stationBean = stationModel.findStation(stationBean);
                request.setAttribute("searchResult", stationBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/searchStation.jsp");
                requestDispatcher.forward(request, response);
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

