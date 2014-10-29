package ru.tsystems.tsproject.sbb.servlet.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.tsproject.sbb.CustomApplicationContext;
import ru.tsystems.tsproject.sbb.ValidationBean;
import ru.tsystems.tsproject.sbb.Validator;
import ru.tsystems.tsproject.sbb.bean.StationBean;
import ru.tsystems.tsproject.sbb.bean.TrainBean;
import ru.tsystems.tsproject.sbb.model.TestClass;
import ru.tsystems.tsproject.sbb.model.TrainModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Servlet, which gets and proceeds requests of station timetable searching
 * @author  Nikita Efremov
 * @since   1.0
 */
@Component
public class SearchStationServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(SearchStationServlet.class);

    private TrainModel trainModel;

    @Autowired
    private TestClass testClass;

    /**
     * Initialize servlet`s attribute - trainModel
     */
    public void init() {
        trainModel = CustomApplicationContext.getTrainModel();
    }

    /**
     * Method proceeds both GET and POST requests. It proceeds request of station timetable searching
     * and after proceeding sends result to view
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
        if (testClass == null) {
            log.warn("Autowiring error");
        } else {
            log.info("autowire success: " + testClass.getSs());
        }
        String action = request.getParameter("stationSearchAction");
        if (action == null) {
            response.sendRedirect("/ui/common/searchStation.jsp");
        } else if (action.equals("back")) {
            response.sendRedirect("/ui/index.jsp");
        } else {
            ValidationBean validationBean = Validator.validate(stationBean);
            if (validationBean.isValidationFailed()) {
                request.setAttribute("validationBean", validationBean);
                request.setAttribute("searchResult", stationBean);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/searchStation.jsp");
                requestDispatcher.forward(request, response);
            } else {
                Collection<TrainBean> trains = trainModel.findTrainsByStation(stationBean);
                if (stationBean.isProcessingFailed()) {
                    request.setAttribute("searchResult", stationBean);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/common/searchStation.jsp");
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

    public TrainModel getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(TrainModel trainModel) {
        this.trainModel = trainModel;
    }

    public TestClass getTestClass() {
        return testClass;
    }

    public void setTestClass(TestClass testClass) {
        this.testClass = testClass;
    }
}

