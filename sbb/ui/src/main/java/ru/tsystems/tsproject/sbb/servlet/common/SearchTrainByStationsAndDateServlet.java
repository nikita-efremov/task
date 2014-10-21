package ru.tsystems.tsproject.sbb.servlet.common;

import ru.tsystems.tsproject.sbb.ApplicationContext;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TimetableBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.StationModel;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Servlet launches searching new train by specifies stations and date, than it analyzes result and send to view
 * @author  Nikita Efremov
 * @since   1.0
 */

public class SearchTrainByStationsAndDateServlet extends HttpServlet {

    private TrainModel trainModel;

    /**
     * Initialize servlet`s attribute - trainModel
     */
    public void init() {
        trainModel = ApplicationContext.getTrainModel();
    }

    /**
     * Method proceeds both GET and POST requests. It launches train searching, analyses result of searching, send result to view
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
        String action = request.getParameter("stationDateTrainSearchAction");
        if (action == null) {
            response.sendRedirect("/ui/common/searchStationDateTrain.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/index.jsp");
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            String startDateString = request.getParameter("Start date");
            String endDateString = request.getParameter("End date");
            Date startDate;
            Date endDate;
            try {
                startDate = simpleDateFormat.parse(startDateString);
            } catch (ParseException e) {
                startDate = null;
            }
            try {
                endDate = simpleDateFormat.parse(endDateString);
            } catch (ParseException e) {
                endDate = null;
            }

            TimetableBean startBean = new TimetableBean();
            startBean.setStationName(request.getParameter("Station start name"));
            startBean.setDate(startDate);

            TimetableBean endBean = new TimetableBean();
            endBean.setStationName(request.getParameter("Station end name"));
            endBean.setDate(endDate);

            startBean.validate("stationName");
            startBean.validate("date");
            endBean.validate("stationName");
            endBean.validate("date");
            if ((startBean.isValidationFailed()) || (endBean.isValidationFailed())) {
                request.setAttribute("startBean", startBean);
                request.setAttribute("endBean", endBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/searchStationDateTrain.jsp");
                requestDispatcher.forward(request, response);
            } else {
                Collection<TrainBean> trains = trainModel.findTrainsByStationsAndDate(startBean, endBean);
                if ((startBean.isProcessingFailed()) || (endBean.isProcessingFailed())) {
                    request.setAttribute("startBean", startBean);
                    request.setAttribute("endBean", endBean);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/searchStationDateTrain.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("foundTrains", trains);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/viewFoundTrains.jsp");
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

