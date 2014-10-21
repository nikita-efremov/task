package ru.tsystems.tsproject.sbb.servlet.administrator;

import ru.tsystems.tsproject.sbb.ApplicationContext;
import ru.tsystems.tsproject.sbb.bean.PassengerBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Servlet launches searching new train, than it analyzes result and send to view
 * @author  Nikita Efremov
 * @since   1.0
 */

public class SearchTrainServlet extends HttpServlet {

    private TrainModel trainModel;

    /**
     * Initialize servlet`s attribute - trainModel
     */
    public void init() {
        trainModel = ApplicationContext.getTrainModel();
    }

    /**
     * Method proceeds both GET and POST requests. It launches train creation, analyses result of creation, send result to view
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
        String action = request.getParameter("trainSearchAction");
        if (action == null) {
            response.sendRedirect("/ui/administrator/train/searchTrain.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/administrator/administratorMain.jsp");
        } else if (action.equals("watch passengers")) {
            TrainBean trainBean = new TrainBean();
            trainBean.setNumber(request.getParameter("Train number"));
            Collection<PassengerBean> passengerBeanSet = trainModel.findTrainPassengers(trainBean);
            request.setAttribute("trainPassengers", passengerBeanSet);
            request.setAttribute("trainBean", trainBean);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/passengers/passengersOnTrain.jsp");
            requestDispatcher.forward(request, response);
        } else if (action.equals("watch timetable")) {
            TrainBean trainBean = new TrainBean();
            trainBean.setNumber(request.getParameter("Train number"));
            trainBean = trainModel.findTrain(trainBean);
            request.setAttribute("trainBean", trainBean);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/timetable/trainTimetable.jsp");
            requestDispatcher.forward(request, response);
        } else {
            TrainBean trainBean = new TrainBean();
            trainBean.setNumber(request.getParameter("Train number"));
            trainBean.validate("number");
            if (trainBean.isValidationFailed()) {
                request.setAttribute("searchResult", trainBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/train/searchTrain.jsp");
                requestDispatcher.forward(request, response);
            } else {
                trainBean = trainModel.findTrain(trainBean);
                request.setAttribute("searchResult", trainBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/administrator/train/searchTrain.jsp");
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
